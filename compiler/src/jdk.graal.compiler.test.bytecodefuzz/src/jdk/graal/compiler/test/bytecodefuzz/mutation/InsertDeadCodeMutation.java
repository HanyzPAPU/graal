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

public class InsertDeadCodeMutation extends AbstractMutation {
    @Override
    protected Function<MethodVisitor,MethodVisitor> createMethodVisitorFactory(ClassNode cn, MethodNode mn, PseudoRandom prng) {
        
        InstructionVisitor iv = new InstructionVisitor(Opcodes.ASM9);
        mn.accept(iv);
        int iindex = prng.indexIn(iv.iindex());

        return mv -> {
            AnalyzerAdapter analyzer = new AnalyzerAdapter(cn.name, mn.access, mn.name, mn.desc, mv);
            return new InsertValuePushMethodVisitor(Opcodes.ASM9, analyzer, iindex, prng, cn, mn) {
                @Override
                protected void afterPush(Type type) {
                    if (type.getSort() == Type.LONG || type.getSort() == Type.DOUBLE) {
                        mv.visitInsn(Opcodes.POP2);
                    }
                    else {
                        mv.visitInsn(Opcodes.POP);
                    }
                }
            };
        };
    }
}