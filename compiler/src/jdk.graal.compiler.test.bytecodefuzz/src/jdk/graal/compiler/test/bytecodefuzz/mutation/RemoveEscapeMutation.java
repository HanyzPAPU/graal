package jdk.graal.compiler.test.bytecodefuzz.mutation;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AnalyzerAdapter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.Type;

import com.code_intelligence.jazzer.mutation.api.PseudoRandom;
import jdk.graal.compiler.api.directives.GraalDirectives;
import jdk.graal.compiler.test.bytecodefuzz.FreeSpace;

public class RemoveEscapeMutation implements NonGrowingMutation {

    private final static String graalDirectivesInternalClassName = Type.getInternalName(GraalDirectives.class);
    private final static String blackholeMethodName = "blackhole";
    
    @Override
    public byte[] mutate(byte[] data, PseudoRandom prng, FreeSpace freeSpace){
        byte[] res = mutate(data, prng);
        if (res == null) return null;
        freeSpace.add(data.length - res.length);
        if (freeSpace.amount() < 0) {
            System.err.println("Removing escape outgrew freespace!!!");
            return null;
        }
        return res;
    }

    private static boolean isBlackhole(String owner, String name) {
        return name.equals(blackholeMethodName) && owner.equals(graalDirectivesInternalClassName);
    }
    
    @Override
    public byte[] mutate(byte[] data, PseudoRandom prng) {
        ClassReader reader = new ClassReader(data);
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassNode cn = new ClassNode(Opcodes.ASM9);
        reader.accept(cn, ClassReader.EXPAND_FRAMES);
        MethodNode mn = MethodSelector.select(cn, prng);

        int escapeCount = (int)StreamSupport.stream(mn.instructions.spliterator(), false)
            .map(a -> (a instanceof MethodInsnNode m) ? m : null)
            .filter(a -> a != null && isBlackhole(a.owner, a.name))
            .count();

        if (escapeCount == 0) {
            System.err.println("Removing escape from method without inserted escapes!");
            return null;
        }

        int targetCall = prng.indexIn(escapeCount);

        ClassVisitor cv = new ClassVisitor(Opcodes.ASM9, writer) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                MethodVisitor mv = this.cv.visitMethod(access, name, descriptor, signature, exceptions);
                if (!name.equals(mn.name)) {
                    return mv;
                }
                else {
                    return new RemoveEscapeMethodVisitor(this.api, mv, targetCall);
                }
            }
        };
        cn.accept(cv);
        return writer.toByteArray();
    }

    private static class RemoveEscapeMethodVisitor extends MethodVisitor {

        private final int targetCall;
        private int currentCall = 0;

        public RemoveEscapeMethodVisitor(int api, MethodVisitor mv, int targetCall) {
            super(api, mv);
            this.targetCall = targetCall;
        }

        private void removeEscape(String descriptor) {
            // Dont visit the call
            // Instead, we need to POP the argument from TOS
            Type[] argTypes = Type.getArgumentTypes(descriptor);
            assert(argTypes.length == 1);
            Type argType = argTypes[0];

            if (argType.getSort() == Type.LONG || argType.getSort() == Type.DOUBLE) {
                this.mv.visitInsn(Opcodes.POP2);
            }
            else {
                this.mv.visitInsn(Opcodes.POP);
            }
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
            if (isBlackhole(owner, name)) {
                if (targetCall == currentCall) {
                    removeEscape(descriptor);
                    return;
                }
                currentCall++;
            }
            this.mv.visitMethodInsn(opcode, name, owner, descriptor, isInterface);
        }
    }
}