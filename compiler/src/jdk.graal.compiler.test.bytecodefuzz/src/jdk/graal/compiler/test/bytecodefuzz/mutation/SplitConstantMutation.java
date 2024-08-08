package jdk.graal.compiler.test.bytecodefuzz.mutation;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import com.code_intelligence.jazzer.mutation.api.PseudoRandom;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import java.util.List;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class SplitConstantMutation extends AbstractMutation {

    @Override
    protected Function<MethodVisitor,MethodVisitor> createMethodVisitorFactory(ClassNode cn, MethodNode mn, PseudoRandom prng) {
        LoadConstantLocator locator = new LoadConstantLocator(Opcodes.ASM9, true);
        mn.accept(locator);

        List<Integer> locations = locator.getLocations();

        if (locations.isEmpty()) {
            throw new RuntimeException("Split constant mutation called on method without constants!");
        }

        int location = prng.pickIn(locations);
        return mv -> new SplitConstantMethodVisitor(Opcodes.ASM9, mv, location, prng);
    }

    private static class SplitConstantMethodVisitor extends InstructionVisitor {

        private final int constantLocation;
        private final PseudoRandom prng;
               
        public SplitConstantMethodVisitor(int api, MethodVisitor mv, int constantLocation, PseudoRandom prng) {
            super(api, mv);
            this.constantLocation = constantLocation;
            this.prng = prng;
        }

        // TODO: add floats and doubles?

        private final List<BiConsumer<Integer, Integer>> intSplitters = Arrays.asList(
            (val, rand) -> { mv.visitLdcInsn(val - rand); mv.visitLdcInsn(rand); mv.visitInsn(Opcodes.IADD);},
            (val, rand) -> { mv.visitLdcInsn(rand); mv.visitLdcInsn(rand - val); mv.visitInsn(Opcodes.ISUB);},
            (val, rand) -> { mv.visitLdcInsn(rand & val); mv.visitLdcInsn((~rand) & val); mv.visitInsn(Opcodes.IOR);},
            (val, rand) -> { mv.visitLdcInsn(rand & val); mv.visitLdcInsn((~rand) & val); mv.visitInsn(Opcodes.IXOR);},
            (val, rand) -> { mv.visitLdcInsn(rand | val); mv.visitLdcInsn((~rand) | val); mv.visitInsn(Opcodes.IAND);}
        );

        private final List<BiConsumer<Long, Long>> longSplitters = Arrays.asList(
            (val, rand) -> { mv.visitLdcInsn(val - rand); mv.visitLdcInsn(rand); mv.visitInsn(Opcodes.LADD);},
            (val, rand) -> { mv.visitLdcInsn(rand); mv.visitLdcInsn(rand - val); mv.visitInsn(Opcodes.LSUB);},
            (val, rand) -> { mv.visitLdcInsn(rand & val); mv.visitLdcInsn((~rand) & val); mv.visitInsn(Opcodes.LOR);},
            (val, rand) -> { mv.visitLdcInsn(rand & val); mv.visitLdcInsn((~rand) & val); mv.visitInsn(Opcodes.LXOR);},
            (val, rand) -> { mv.visitLdcInsn(rand | val); mv.visitLdcInsn((~rand) | val); mv.visitInsn(Opcodes.LAND);}
        );

        private void split(int value) {
            int rand = prng.closedRange(Integer.MIN_VALUE, Integer.MAX_VALUE);
            prng.pickIn(intSplitters).accept(value, rand);
        }

        private void split(long value) {
            long rand = prng.nextLong();
            prng.pickIn(longSplitters).accept(value, rand);
        }

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

        private void split(String value) {
            int rand = prng.closedRange(0, value.length());
            String left = value.substring(0, rand);
            String right = value.substring(rand);
            mv.visitLdcInsn(left);
            mv.visitLdcInsn(right);
            visitStringConcatCall();            
        }

        @Override
        public void visitLdcInsn(Object value){
            if (iindex() == constantLocation) {
                if (value instanceof Integer intVal) {
                    split(intVal);
                }
                if (value instanceof Long longVal) {
                    split(longVal);
                }
                if (value instanceof String stringVal) {
                    split(stringVal);
                }
                super.visitInstructionInternal();
            }
            else {
                super.visitLdcInsn(value);
            }
        }

        @Override
        public void visitIntInsn(int opcode, int operand) {
            if (iindex() == constantLocation) {
                split(operand);
                super.visitInstructionInternal();
            }
            else {
                super.visitIntInsn(opcode, operand);
            }
        }
       
        @Override
        public void visitInsn(int opcode) {
            if (iindex() == constantLocation) {
                if (LoadConstantLocator.isOpcodeICONST(opcode)) {
                    int value = switch(opcode) {
                        case (Opcodes.ICONST_M1) -> -1;
                        case (Opcodes.ICONST_0) -> 0;
                        case (Opcodes.ICONST_1) -> 1;
                        case (Opcodes.ICONST_2) -> 2;
                        case (Opcodes.ICONST_3) -> 3;
                        case (Opcodes.ICONST_4) -> 4;
                        case (Opcodes.ICONST_5) -> 5;
                        default -> {
                            throw new RuntimeException("Invalid opcode selected!");
                        }

                    };
                    split(value);
                }
                if (LoadConstantLocator.isOpcodeLCONST(opcode)) {
                    long value = switch(opcode) {
                        case (Opcodes.LCONST_0) -> 0;
                        case (Opcodes.LCONST_1) -> 1;
                        default -> {
                            throw new RuntimeException("Invalid opcode selected!");
                        }
                    };
                    split(value);
                }
                super.visitInstructionInternal();
            }
            else {
                super.visitInsn(opcode);
            }            
            
        }
    }
}
