package jdk.graal.compiler.test.bytecodefuzz.mutation;

import java.util.function.Function;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import com.code_intelligence.jazzer.mutation.api.PseudoRandom;

public abstract class AbstractMutation implements Mutation {
    public final byte[] mutate(byte[] data, PseudoRandom prng) {
        ClassReader reader = new ClassReader(data);
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassNode cn = new ClassNode(Opcodes.ASM9);
        reader.accept(cn, ClassReader.EXPAND_FRAMES);

        MethodNode mn = MethodSelector.select(cn, prng);
        Function<MethodVisitor,MethodVisitor> mvFactory = createMethodVisitorFactory(cn, mn, prng);

        ClassVisitor cv = new ClassVisitor(Opcodes.ASM9, writer) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                MethodVisitor mv = this.cv.visitMethod(access, name, descriptor, signature, exceptions);
                if (!name.equals(mn.name)) {
                    return mv;
                }
                return mvFactory.apply(mv);
            }
        };
        cn.accept(cv);
        return writer.toByteArray();
    }

    protected abstract Function<MethodVisitor,MethodVisitor> createMethodVisitorFactory(ClassNode cn, MethodNode mn, PseudoRandom prng);
}