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

public class InsertNeutralArithmeticMutation extends AbstractMutation {
    //                                                                               Simple type represented by 1 entry                      Complex type with 2 entries, type + top                                   String
    private static final Pattern numberOnTosPattern = Pattern.compile("\\[(.*, )*((?<type1>" + Opcodes.INTEGER + "|" + Opcodes.FLOAT + ")|((?<type2>"+ Opcodes.LONG + "|" + Opcodes.DOUBLE + "), " + Opcodes.TOP +")|(?<string>"+ Type.getInternalName(String.class) +"))\\]");

    private static final int STRING_TYPE = -1;

    private static class Pair <T1, T2> {
        public final T1 first;
        public final T2 second;
        public Pair(T1 first, T2 second){
            this.first = first;
            this.second = second;
        }
    }

    @Override
    protected Function<MethodVisitor,MethodVisitor> createMethodVisitorFactory(ClassNode cn, MethodNode mn, PseudoRandom prng) {
        FrameMapAnalyzer frameMapAnalyzer = new FrameMapAnalyzer(Opcodes.ASM9, cn.name, mn.access, mn.name, mn.desc, false);
        mn.accept(frameMapAnalyzer);
        
        List<Pair<Integer, Integer>> validProgramPoints = frameMapAnalyzer.getMap().entrySet().stream()
            .flatMap(e -> {
                Matcher matcher = numberOnTosPattern.matcher(e.getKey());
                if (matcher.matches()) {
                    String type1 = matcher.group("type1");
                    String type2 = matcher.group("type2");
                    String stringType = matcher.group("string");

                    if (stringType == null) {
                        int type = Integer.parseInt(type1 != null ? type1 : type2);
                        return e.getValue().stream().map(iindex -> new Pair<Integer, Integer>(type, iindex));
                    }
                    else {
                        return e.getValue().stream().map(iindex -> new Pair<Integer, Integer>(STRING_TYPE, iindex));
                    }
                }
                else {
                    return Stream.empty();
                }
            })
            .collect(Collectors.toList());

        if (validProgramPoints.isEmpty()) {
            throw new RuntimeException("Insert neutral arithmetic mutator selected a method without number on TOS!");
        }

        Pair<Integer, Integer> typedIndex = prng.pickIn(validProgramPoints);
        int pickedType = typedIndex.first;
        int pickedIndex = typedIndex.second;

        return mv -> new InsertNeutralOpMethodVisitor(Opcodes.ASM9, mv, pickedIndex, pickedType, prng);
    }
    
    private static class InsertNeutralOpMethodVisitor extends InstructionVisitor {
        
        private final int opIindex;
        private final int type;
        PseudoRandom prng;

        // TODO: support string concat with "" ?

        public InsertNeutralOpMethodVisitor(int api, MethodVisitor mv, int opIindex, int type, PseudoRandom prng) {
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
            () -> {mv.visitInsn(Opcodes.INEG); mv.visitInsn(Opcodes.INEG);},        // ~(~tos)
            () -> {mv.visitInsn(Opcodes.ICONST_0); mv.visitInsn(Opcodes.ISHL);},    // tos << 0
            () -> {mv.visitInsn(Opcodes.ICONST_0); mv.visitInsn(Opcodes.ISHR);},    // tos >> 0
            () -> {mv.visitInsn(Opcodes.ICONST_0); mv.visitInsn(Opcodes.IUSHR);},   // tos >>> 0
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
            () -> {mv.visitInsn(Opcodes.LNEG); mv.visitInsn(Opcodes.LNEG);},        // ~(~tos)
            () -> {mv.visitInsn(Opcodes.LCONST_0); mv.visitInsn(Opcodes.LSHL);},    // tos << 0
            () -> {mv.visitInsn(Opcodes.LCONST_0); mv.visitInsn(Opcodes.LSHR);},    // tos >> 0
            () -> {mv.visitInsn(Opcodes.LCONST_0); mv.visitInsn(Opcodes.LUSHR);},   // tos >>> 0
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

        private final Map<Integer, Runnable[]> variants = Map.of(
            Opcodes.INTEGER, intVariants,
            Opcodes.FLOAT, floatVariants,
            Opcodes.LONG, longVariants,
            Opcodes.DOUBLE, doubleVariants,
            STRING_TYPE, stringVariants
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