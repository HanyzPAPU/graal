package jdk.graal.compiler.test.bytecodefuzz.mutation;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AnalyzerAdapter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.Type;

import com.code_intelligence.jazzer.mutation.api.PseudoRandom;
import jdk.graal.compiler.api.directives.GraalDirectives;

public class InsertEscapeMutation extends AbstractMutation {
    @Override
    protected Function<MethodVisitor,MethodVisitor> createMethodVisitorFactory(ClassNode cn, MethodNode mn, PseudoRandom prng) {
        InstructionVisitor iv = new InstructionVisitor(Opcodes.ASM9);
        mn.accept(iv);
        // Don't insert escapes before the first instruction
        // This would only ever escape `this`
        int iindex = prng.otherIndexIn(iv.iindex(), 0);

        return mv -> {
            AnalyzerAdapter analyzer = new AnalyzerAdapter(cn.name, mn.access, mn.name, mn.desc, mv);
            return new InsertEscapeMethodVisitor(Opcodes.ASM9, analyzer, iindex, prng);
        };
    }

    private static class InsertEscapeMethodVisitor extends InstructionVisitor {
        private final AnalyzerAdapter analyzer;
        private final int targetIindex;
        private final PseudoRandom prng;

        private final static String graalDirectivesInternalClassName = Type.getInternalName(GraalDirectives.class);
        private final static String blackholeMethodName = "blackhole";

        public InsertEscapeMethodVisitor(int api, AnalyzerAdapter analyzer, int targetIindex, PseudoRandom prng) {
            super(api, analyzer);
            this.analyzer = analyzer;
            this.targetIindex = targetIindex;
            this.prng = prng;
        }

        private static String getBlackholeDesc(Object type) {
            Type argType;
            if (type instanceof Integer) {
                argType = AsmTypeSupport.getType(type);
            }
            else {
                argType = AsmTypeSupport.objectType;
            }
            return Type.getMethodDescriptor(Type.VOID_TYPE, argType);
        }

        private void escapeTos() {
            assert(analyzer.stack != null);

            Object tosType = analyzer.stack.getLast();
            boolean wide = false;
            if (tosType.equals(Opcodes.TOP)) {
                tosType = analyzer.stack.get(analyzer.stack.size() - 2);
                wide = true;
            }
            String blackholeDesc = getBlackholeDesc(tosType);

            mv.visitInsn(wide ? Opcodes.DUP2 : Opcodes.DUP);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, graalDirectivesInternalClassName, blackholeMethodName, blackholeDesc, false);
        }

        private void escapeVar() {
            assert(analyzer.locals != null);

            List<Integer> esapableVars = getEscapableVars().boxed().toList();

            int localIdx = prng.pickIn(esapableVars);
            Object typeObj = analyzer.locals.get(localIdx);

            Type localType = AsmTypeSupport.getType(typeObj);
            String blackholeDesc = getBlackholeDesc(typeObj);
            mv.visitIntInsn(localType.getOpcode(Opcodes.ILOAD), localIdx);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, graalDirectivesInternalClassName, blackholeMethodName, blackholeDesc, false);
        }

        private boolean canEscapeTos() {
            if (analyzer.stack == null) return false;
            if (analyzer.stack.isEmpty()) return false;
            Object tosType = analyzer.stack.getLast();
            if (tosType.equals(Opcodes.TOP)) {
                tosType = analyzer.stack.get(analyzer.stack.size() - 2);
            }
            if (tosType instanceof Integer iTosType) {
                if (iTosType.equals(Opcodes.TOP) || iTosType.equals(Opcodes.NULL) || iTosType.equals(Opcodes.UNINITIALIZED_THIS)) {
                    return false;
                }
                else {
                    return true;
                }
            }
            if (tosType instanceof String) {
                return true;
            }
            return false;
        }

        private IntStream getEscapableVars() {

            return IntStream
                .range(0, analyzer.locals.size())
                .filter(i -> {
                    Object typeObj = analyzer.locals.get(i);
                    return typeObj != Opcodes.NULL && AsmTypeSupport.getType(typeObj) != null;
                });
        }

        private boolean canEscapeVar() {
            if (analyzer.locals == null) return false;
            return getEscapableVars().findFirst().isPresent();
        }

        private void insertEscape() {
            if (!canEscapeTos() && !canEscapeVar()) {
                throw new MutationFailedException("Cannot insert escape on iindex with empty stack and without locals!");
            }
            
            boolean escapeTos = canEscapeTos() && (!canEscapeVar() || prng.choice());

            if (escapeTos) {
                escapeTos();
            }
            else {
                escapeVar();
            }
        }

        @Override
        public void visitInstruction() {
            if (iindex() == targetIindex) {
                insertEscape();
            }
        }

    }
}