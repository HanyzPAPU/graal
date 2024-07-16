package jdk.graal.compiler.test.bytecodefuzz;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Function;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.LocalVariablesSorter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import com.code_intelligence.jazzer.mutation.api.PseudoRandom;

public class InsertNeutralArithmeticMutation implements Mutation {

    private static final String intOnTosSigEnd = Opcodes.INTEGER + "]";
    
    public void mutate(ClassReader reader, ClassWriter writer, FreeSpace freeSpace, PseudoRandom prng) {
        ClassNode cn = new ClassNode(Opcodes.ASM9);
        reader.accept(cn, ClassReader.EXPAND_FRAMES);

        MethodNode mn = MethodSelector.select(cn, prng);

        FrameMapAnalyzer frameMapAnalyzer = new FrameMapAnalyzer(Opcodes.ASM9, cn.name, mn.access, mn.name, mn.desc, false);
        mn.accept(frameMapAnalyzer);

        Integer[] validProgramPoints = frameMapAnalyzer.getMap().keySet().stream()
            .filter(k -> k.endsWith(intOnTosSigEnd))
            .flatMap(k -> frameMapAnalyzer.getMap().get(k).stream())
            .toArray(Integer[]::new);

        if (validProgramPoints.length == 0) {
            System.err.println("Insert neutral arithmetic mutator selected a method without int on TOS!");
            return;
        }

        int iindex = prng.pickIn(validProgramPoints);

        ClassVisitor insertOpClassVisitor = new ClassVisitor(Opcodes.ASM9, writer) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                MethodVisitor mv = this.cv.visitMethod(access, name, descriptor, signature, exceptions);
                if (!name.equals(mn.name)) {
                    return mv;
                }
                return new InsertNeutralOpMethodVisitor(api, mv, iindex, prng);
            }
        };

        cn.accept(insertOpClassVisitor);

        // TODO: handle in a better way!
        freeSpace.add(reader.b.length - writer.toByteArray().length);
        if (freeSpace.amount() < 0) {
            System.err.println("Exceeded maximal Size!!!");
        }
    }

    private static class InsertNeutralOpMethodVisitor extends InstructionVisitor {
        
        private final int opIindex;
        PseudoRandom prng;

        public InsertNeutralOpMethodVisitor(int api, MethodVisitor mv, int opIindex, PseudoRandom prng) {
            super(api, mv);
            this.opIindex = opIindex;
            this.prng = prng;
        }

        private final Runnable[] variants = new Runnable[] {
            () -> {mv.visitInsn(Opcodes.ICONST_0); mv.visitInsn(Opcodes.IADD);},    // tos + 0
            () -> {mv.visitInsn(Opcodes.ICONST_0); mv.visitInsn(Opcodes.ISUB);},    // tos - 0
            () -> {mv.visitInsn(Opcodes.ICONST_1); mv.visitInsn(Opcodes.IMUL);},    // tos * 1
            () -> {mv.visitInsn(Opcodes.ICONST_1); mv.visitInsn(Opcodes.IDIV);},    // tos / 1
            () -> {mv.visitInsn(Opcodes.ICONST_M1); mv.visitInsn(Opcodes.IAND);},   // tos & 0xFFFFFFFF
            () -> {mv.visitInsn(Opcodes.ICONST_0); mv.visitInsn(Opcodes.IOR);},     // tos | 0
            () -> {mv.visitInsn(Opcodes.ICONST_0); mv.visitInsn(Opcodes.IXOR);},    // tos ^ 0
            () -> {mv.visitInsn(Opcodes.INEG); mv.visitInsn(Opcodes.INEG);},        // ~(~tos)
            () -> {mv.visitInsn(Opcodes.ICONST_0); mv.visitInsn(Opcodes.ISHL);},    // tos << 0
            () -> {mv.visitInsn(Opcodes.ICONST_0); mv.visitInsn(Opcodes.ISHR);},    // tos >> 0
            () -> {mv.visitInsn(Opcodes.ICONST_0); mv.visitInsn(Opcodes.IUSHR);},   // tos >>> 0
            () -> {mv.visitLdcInsn(prng.closedRange(Integer.MIN_VALUE, Integer.MAX_VALUE)); mv.visitInsn(Opcodes.POP);}    // push random; pop
        };

        private void insertOp() {
            prng.pickIn(variants).run();
        }
        
        @Override
        public void visitInstruction() {
            if (iindex() == opIindex) {
                insertOp();
            }
        }
    }
}