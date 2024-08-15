package jdk.graal.compiler.test.bytecodefuzz.mutation;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AnalyzerAdapter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.Type;

import com.code_intelligence.jazzer.mutation.api.PseudoRandom;
import jdk.graal.compiler.api.directives.GraalDirectives;

public class InsertEscapeMutation extends AbstractMutation {
    @Override
    protected Function<MethodVisitor,MethodVisitor> createMethodVisitorFactory(ClassNode cn, MethodNode mn, PseudoRandom prng) {
        InstructionVisitor iv = new InstructionVisitor(Opcodes.ASM9);
        mn.accept(iv);
        // Don't insert escapes before first instruction
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
            if (type instanceof Integer i) {
                argType = AsmTypeSupport.getType(type);
            }
            else {
                argType = Type.getType(Object.class);
            }
            return Type.getMethodDescriptor(Type.VOID_TYPE, argType);
        }

        private void escapeTos() {
            assert(analyzer.stack != null);

            Object tosType = analyzer.stack.get(analyzer.stack.size() - 1);
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
            int localIdx = prng.indexIn(analyzer.locals);
            boolean wide = false;
            if (analyzer.locals.get(localIdx).equals(Opcodes.TOP)) {
                localIdx--;
                wide = true;
            }

            Type localType = AsmTypeSupport.getType(analyzer.locals.get(localIdx));
            String blackholeDesc = getBlackholeDesc(analyzer.locals.get(localIdx));
            mv.visitIntInsn(localType.getOpcode(Opcodes.ILOAD), localIdx);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, graalDirectivesInternalClassName, blackholeMethodName, blackholeDesc, false);
        }

        private void insertEscape() {
            if (analyzer.stack == null || analyzer.locals == null || (analyzer.locals.isEmpty() && analyzer.stack.isEmpty())) {
                throw new RuntimeException("Cannot insert escape on iindex with empty stack and without locals!");
            }
            
            boolean escapeTos = !analyzer.stack.isEmpty() && (analyzer.locals.isEmpty() || prng.choice());

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