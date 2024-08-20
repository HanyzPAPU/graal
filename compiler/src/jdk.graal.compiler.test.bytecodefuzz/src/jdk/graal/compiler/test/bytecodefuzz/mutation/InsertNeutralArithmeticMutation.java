package jdk.graal.compiler.test.bytecodefuzz.mutation;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import com.code_intelligence.jazzer.mutation.api.PseudoRandom;
import jdk.graal.compiler.test.bytecodefuzz.Pair;

public class InsertNeutralArithmeticMutation extends AbstractMutation {
    
    @Override
    protected Function<MethodVisitor,MethodVisitor> createMethodVisitorFactory(ClassNode cn, MethodNode mn, PseudoRandom prng) {
        
        List<Pair<Integer, Type>> validProgramPoints = PrimitiveOnTosLocator.getLocations(cn, mn);

        if (validProgramPoints.isEmpty()) {
            throw new RuntimeException("Insert neutral op mutation selected a method without primitive on TOS!");
        }

        Pair<Integer, Type> typedIndex = prng.pickIn(validProgramPoints);
        Type pickedType = typedIndex.second;
        int pickedIndex = typedIndex.first;

        return mv -> new InsertNeutralOpMethodVisitor(Opcodes.ASM9, mv, pickedIndex, pickedType, prng);
    }
    
    private static class InsertNeutralOpMethodVisitor extends InstructionVisitor {
        
        private final int opIindex;
        private final Type type;
        PseudoRandom prng;

        public InsertNeutralOpMethodVisitor(int api, MethodVisitor mv, int opIindex, Type type, PseudoRandom prng) {
            super(api, mv);
            this.opIindex = opIindex;
            this.prng = prng;
            this.type = type;
        }

        private final Runnable[] intVariants = new Runnable[] {
            () -> {mv.visitInsn(Opcodes.ICONST_0); mv.visitInsn(Opcodes.IADD);},    // tos + 0
            () -> {mv.visitInsn(Opcodes.ICONST_0); mv.visitInsn(Opcodes.ISUB);},    // tos - 0
            () -> {mv.visitInsn(Opcodes.ICONST_1); mv.visitInsn(Opcodes.IMUL);},    // tos * 1
            () -> {mv.visitInsn(Opcodes.ICONST_1); mv.visitInsn(Opcodes.IDIV);},    // tos / 1
            () -> {mv.visitInsn(Opcodes.ICONST_M1); mv.visitInsn(Opcodes.IAND);},   // tos & 0xFFFFFFFF
            () -> {mv.visitInsn(Opcodes.ICONST_0); mv.visitInsn(Opcodes.IOR);},     // tos | 0
            () -> {mv.visitInsn(Opcodes.ICONST_0); mv.visitInsn(Opcodes.IXOR);},    // tos ^ 0
            () -> {mv.visitInsn(Opcodes.INEG); mv.visitInsn(Opcodes.INEG);},        // -(-tos)
            () -> {mv.visitInsn(Opcodes.ICONST_0); mv.visitInsn(Opcodes.ISHL);},    // tos << 0
            () -> {mv.visitInsn(Opcodes.ICONST_0); mv.visitInsn(Opcodes.ISHR);},    // tos >> 0
            () -> {mv.visitInsn(Opcodes.ICONST_0); mv.visitInsn(Opcodes.IUSHR);},   // tos >>> 0
            () -> {mv.visitInsn(Opcodes.I2L); mv.visitInsn(Opcodes.L2I);}           // (int)(long)tos
        };

        private final Runnable[] floatVariants = new Runnable[] {
            () -> {mv.visitInsn(Opcodes.FCONST_0); mv.visitInsn(Opcodes.FADD);},    // tos + 0.0
            () -> {mv.visitLdcInsn(-0.0f); mv.visitInsn(Opcodes.FADD);},            // tos + -0.0
            () -> {mv.visitInsn(Opcodes.FCONST_0); mv.visitInsn(Opcodes.FSUB);},    // tos - 0.0
            () -> {mv.visitLdcInsn(-0.0f); mv.visitInsn(Opcodes.FSUB);},            // tos - -0.0
            () -> {mv.visitInsn(Opcodes.FCONST_1); mv.visitInsn(Opcodes.FMUL);},    // tos * 1.0
            () -> {mv.visitInsn(Opcodes.FCONST_1); mv.visitInsn(Opcodes.FDIV);},    // tos / 1.0
            () -> {mv.visitInsn(Opcodes.FNEG); mv.visitInsn(Opcodes.FNEG);},        // -(-tos)
        };
        private final Runnable[] longVariants = new Runnable[] {
            () -> {mv.visitInsn(Opcodes.LCONST_0); mv.visitInsn(Opcodes.LADD);},    // tos + 0
            () -> {mv.visitInsn(Opcodes.LCONST_0); mv.visitInsn(Opcodes.LSUB);},    // tos - 0
            () -> {mv.visitInsn(Opcodes.LCONST_1); mv.visitInsn(Opcodes.LMUL);},    // tos * 1
            () -> {mv.visitInsn(Opcodes.LCONST_1); mv.visitInsn(Opcodes.LDIV);},    // tos / 1
            () -> {mv.visitLdcInsn(-1L); mv.visitInsn(Opcodes.LAND);},              // tos & 0xFFFFFFFFFFFFFFFF
            () -> {mv.visitInsn(Opcodes.LCONST_0); mv.visitInsn(Opcodes.LOR);},     // tos | 0
            () -> {mv.visitInsn(Opcodes.LCONST_0); mv.visitInsn(Opcodes.LXOR);},    // tos ^ 0
            () -> {mv.visitInsn(Opcodes.LNEG); mv.visitInsn(Opcodes.LNEG);},        // -(-tos)
            () -> {mv.visitInsn(Opcodes.ICONST_0); mv.visitInsn(Opcodes.LSHL);},    // tos << 0
            () -> {mv.visitInsn(Opcodes.ICONST_0); mv.visitInsn(Opcodes.LSHR);},    // tos >> 0
            () -> {mv.visitInsn(Opcodes.ICONST_0); mv.visitInsn(Opcodes.LUSHR);},   // tos >>> 0
        };
        private final Runnable[] doubleVariants = new Runnable[] {
            () -> {mv.visitInsn(Opcodes.DCONST_0); mv.visitInsn(Opcodes.DADD);},    // tos + 0.0
            () -> {mv.visitLdcInsn(-0.0d); mv.visitInsn(Opcodes.DADD);},            // tos + -0.0
            () -> {mv.visitInsn(Opcodes.DCONST_0); mv.visitInsn(Opcodes.DSUB);},    // tos - 0.0
            () -> {mv.visitLdcInsn(-0.0d); mv.visitInsn(Opcodes.DSUB);},            // tos - -0.0
            () -> {mv.visitInsn(Opcodes.DCONST_1); mv.visitInsn(Opcodes.DMUL);},    // tos * 1.0
            () -> {mv.visitInsn(Opcodes.DCONST_1); mv.visitInsn(Opcodes.DDIV);},    // tos / 1.0
            () -> {mv.visitInsn(Opcodes.DNEG); mv.visitInsn(Opcodes.DNEG);},        // -(-tos)
        };

        private final Runnable[] stringVariants = new Runnable[] {
            () -> {mv.visitLdcInsn(""); visitStringConcatCall(); },                 // tos + ""
        };

        private void visitStringConcatCall() {
            String owner =  Type.getInternalName(String.class);
            String name = "concat";
            String descriptor;
            try {
                descriptor = Type.getMethodDescriptor(String.class.getMethod(name, String.class));
            }
            catch(Throwable e) {
                throw new RuntimeException("String concat method not found!");
            }
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, owner, name, descriptor, false);
        }

        private final Map<Type, Runnable[]> variants = Map.of(
            Type.INT_TYPE, intVariants,
            Type.FLOAT_TYPE, floatVariants,
            Type.LONG_TYPE, longVariants,
            Type.DOUBLE_TYPE, doubleVariants,
            AsmTypeSupport.stringType, stringVariants
        );

        private void insertOp() {
            prng.pickIn(variants.get(type)).run();
        }
        
        @Override
        public void visitInstruction() {
            if (iindex() == opIindex) {
                insertOp();
            }
        }
    }
}