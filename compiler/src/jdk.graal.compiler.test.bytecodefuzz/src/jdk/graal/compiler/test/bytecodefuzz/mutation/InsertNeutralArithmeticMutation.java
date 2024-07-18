package jdk.graal.compiler.test.bytecodefuzz.mutation;

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

public class InsertNeutralArithmeticMutation extends AbstractMutation {

    // TODO: what if a class name ended with a 1? -> refactor
    private static final String intOnTosSigEnd = Opcodes.INTEGER + "]";

    @Override
    protected Function<MethodVisitor,MethodVisitor> createMethodVisitorFactory(ClassNode cn, MethodNode mn, PseudoRandom prng) {
        FrameMapAnalyzer frameMapAnalyzer = new FrameMapAnalyzer(Opcodes.ASM9, cn.name, mn.access, mn.name, mn.desc, false);
        mn.accept(frameMapAnalyzer);
        
        Integer[] validProgramPoints = frameMapAnalyzer.getMap().keySet().stream()
            .filter(k -> k.endsWith(intOnTosSigEnd))
            .flatMap(k -> frameMapAnalyzer.getMap().get(k).stream())
            .toArray(Integer[]::new);

        if (validProgramPoints.length == 0) {
            throw new RuntimeException("Insert neutral arithmetic mutator selected a method without int on TOS!");
        }

        int iindex = prng.pickIn(validProgramPoints);
        return mv -> new InsertNeutralOpMethodVisitor(Opcodes.ASM9, mv, iindex, prng);
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