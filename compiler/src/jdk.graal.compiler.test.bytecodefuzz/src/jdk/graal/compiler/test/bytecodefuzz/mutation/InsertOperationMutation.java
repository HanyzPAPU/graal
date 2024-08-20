package jdk.graal.compiler.test.bytecodefuzz.mutation;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;
import java.lang.reflect.Field;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AnalyzerAdapter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.Type;

import com.code_intelligence.jazzer.mutation.api.PseudoRandom;
import jdk.graal.compiler.test.bytecodefuzz.Pair;

public class InsertOperationMutation extends AbstractMutation {


    // Should this be an ancestor of insert neutral op? or a sibling?
    // aren't there like operations that do not have a neutral element?

    @Override
    protected Function<MethodVisitor,MethodVisitor> createMethodVisitorFactory(ClassNode cn, MethodNode mn, PseudoRandom prng) {
        // select from iindeces with primitive type on TOS (String?)
        // binop / unop -> opt push, insert op

        List<Pair<Integer, Type>> validProgramPoints = PrimitiveOnTosLocator.getLocations(cn, mn);

        if (validProgramPoints.isEmpty()) {
            throw new RuntimeException("Insert op mutation selected a method without primitive on TOS!");
        }

        Pair<Integer, Type> typedIndex = prng.pickIn(validProgramPoints);
        Type pickedType = typedIndex.second;
        int pickedIndex = typedIndex.first;

        return mv -> {
            AnalyzerAdapter analyzer = new AnalyzerAdapter(cn.name, mn.access, mn.name, mn.desc, mv);
            return new InsertOperationMethodVisitor(Opcodes.ASM9, analyzer, pickedIndex, pickedType, prng, cn, mn);
        };
    }

    private static class InsertOperationMethodVisitor extends InsertValuePushMethodVisitor {

        private final Type type;

        public InsertOperationMethodVisitor(int api, AnalyzerAdapter analyzer, int targetIindex, Type type, PseudoRandom prng, ClassNode cn, MethodNode mn) {
            super(api, analyzer, targetIindex, prng, cn, mn);
            this.type = type;
        }

        private static final Map<Type, Integer[]> unopOpcodes = Map.of(
            Type.INT_TYPE, new Integer[] {Opcodes.INEG, Opcodes.I2B, Opcodes.I2C, Opcodes.I2S},
            Type.FLOAT_TYPE, new Integer[] {Opcodes.FNEG},
            Type.LONG_TYPE, new Integer[] {Opcodes.LNEG},
            Type.DOUBLE_TYPE, new Integer[] {Opcodes.DNEG}
        );

        private static final Map<Type, Integer[]> binopOpcodes = Map.of(
            Type.INT_TYPE, new Integer[] {Opcodes.IADD, Opcodes.IAND, Opcodes.IDIV, Opcodes.IMUL, Opcodes.IOR, Opcodes.IREM, Opcodes.ISHL, Opcodes.ISHR, Opcodes.ISUB, Opcodes.IUSHR, Opcodes.IXOR},
            Type.FLOAT_TYPE, new Integer[] {Opcodes.FADD, Opcodes.FDIV, Opcodes.FMUL, Opcodes.FREM, Opcodes.FSUB},
            Type.LONG_TYPE, new Integer[] {Opcodes.LADD, Opcodes.LAND, Opcodes.LDIV, Opcodes.LMUL, Opcodes.LOR, Opcodes.LREM, Opcodes.LSUB, Opcodes.LXOR},
            Type.DOUBLE_TYPE, new Integer[] {Opcodes.DADD, Opcodes.DDIV, Opcodes.DMUL, Opcodes.DREM, Opcodes.DSUB}
        );


        private void insertSubstringCall() {
            int beginIndex = prng.indexIn(MIN_ARRAY_SIZE);
            int endIndex = prng.closedRange(beginIndex, MIN_ARRAY_SIZE);
            mv.visitIntInsn(Opcodes.BIPUSH, beginIndex);
            mv.visitIntInsn(Opcodes.BIPUSH, endIndex);
            visitStringCall("substring", int.class, int.class);
        }

        private static final String replaceChars = "qwertyuiopasdfghjklzxcvbnm1234567890-=[]./QWERTYUIOPLKJHGFDSAZXCVBNM!@#$%^&*()_+>?:";

        private void insertStringReplaceCall() {

            int oldIndex = prng.indexIn(replaceChars.length());
            int newIndex = prng.otherIndexIn(replaceChars.length(), oldIndex);

            char oldChar = replaceChars.charAt(oldIndex);
            char newChar = replaceChars.charAt(newIndex);

            mv.visitIntInsn(Opcodes.BIPUSH, oldChar);
            mv.visitIntInsn(Opcodes.BIPUSH, newChar);

            visitStringCall("replace", char.class, char.class);
        }

        private final Runnable[] stringVariants = new Runnable[] {
            this::insertSubstringCall,
            () -> {pushValue(AsmTypeSupport.stringType); visitStringCall("concat", String.class);},
            this::insertStringReplaceCall,
            () -> {visitStringCall("toLowerCase");},
            () -> {visitStringCall("toUpperCase");},
            () -> {visitStringCall("trim");},
        };

        private void visitStringCall(String methodName, Class<?>... args) {
            String owner = AsmTypeSupport.stringType.getInternalName();
            String descriptor;
            try {
                descriptor = Type.getMethodDescriptor(String.class.getMethod(methodName, args));
            }
            catch(Throwable e) {
                throw new RuntimeException("String method " + methodName + " not found!");
            }
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, owner, methodName, descriptor, false);
        }

        private void insertStringOp() {
            prng.pickIn(stringVariants).run();
        }

        private void insertUnop() {
            int opcode = prng.pickIn(unopOpcodes.get(type));
            mv.visitInsn(opcode);
        }

        private void insertBinop() {
            int opcode = prng.pickIn(binopOpcodes.get(type));
            mv.visitInsn(opcode);
        }

        private void insertOp() {

            if (type.equals(AsmTypeSupport.stringType)) {
                insertStringOp();
                return;
            }

            // give an unop the same probability as any other binop
            int unopInverseFreq = 1 + binopOpcodes.get(type).length;

            if (prng.trueInOneOutOf(unopInverseFreq)) {
                insertUnop();
            }
            else {
                pushValue(type);
                insertBinop();
            }
        }

        @Override
        protected void afterPush(Type type) {
            assert(type.equals(this.type));
        }

        @Override
        public void visitInstruction() {
            if (iindex() == targetIindex) {
                insertOp();
            }
        }
    }
}