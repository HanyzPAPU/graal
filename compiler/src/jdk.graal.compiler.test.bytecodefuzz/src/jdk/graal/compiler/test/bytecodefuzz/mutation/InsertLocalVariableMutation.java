package jdk.graal.compiler.test.bytecodefuzz.mutation;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.LocalVariablesSorter;
import org.objectweb.asm.commons.AnalyzerAdapter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.Type;

import com.code_intelligence.jazzer.mutation.api.PseudoRandom;

import jdk.graal.compiler.test.bytecodefuzz.FieldHolder;

public class InsertLocalVariableMutation extends AbstractMutation {
    @Override
    protected Function<MethodVisitor,MethodVisitor> createMethodVisitorFactory(ClassNode cn, MethodNode mn, PseudoRandom prng) {
        InstructionVisitor iv = new InstructionVisitor(Opcodes.ASM9);
        mn.accept(iv);
        int iindex = prng.indexIn(iv.iindex());

        return mv -> {
            AnalyzerAdapter analyzer = new AnalyzerAdapter(cn.name, mn.access, mn.name, mn.desc, mv);
            InsertLocalVariableMethodVisitor insertLocalVisitor = new InsertLocalVariableMethodVisitor(Opcodes.ASM9, analyzer, iindex, prng, cn, mn);
            LocalVariablesSorter locVarSorter = new LocalVariablesSorter(mn.access, mn.desc, insertLocalVisitor);
            insertLocalVisitor.locVarSorter = locVarSorter;
            return locVarSorter;
        };
    }

    private static class InsertLocalVariableMethodVisitor extends InsertValuePushMethodVisitor {
        
        public LocalVariablesSorter locVarSorter;

        public InsertLocalVariableMethodVisitor(int api, AnalyzerAdapter analyzer, int targetIindex, PseudoRandom prng, ClassNode cn, MethodNode mn) {
            super(api, analyzer, targetIindex, prng, cn, mn);
        }

        @Override
        protected void afterPush(Type type){
            int locVarIdx = locVarSorter.newLocal(type);
            this.mv.visitVarInsn(type.getOpcode(Opcodes.ISTORE), locVarIdx);
        }
    }
}