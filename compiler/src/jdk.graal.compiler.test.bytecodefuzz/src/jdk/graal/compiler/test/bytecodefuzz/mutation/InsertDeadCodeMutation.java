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
            return new InsertDeadCodeMethodVisitor(Opcodes.ASM9, analyzer, iindex, prng, cn, mn);
        };
    }

    private static class InsertDeadCodeMethodVisitor extends InstructionVisitor {

        private final int targetIindex;
        private final PseudoRandom prng;
        private final ClassNode cn;
        private final MethodNode mn;
        private final AnalyzerAdapter analyzer;

        public InsertDeadCodeMethodVisitor(int api, AnalyzerAdapter analyzer, int targetIindex, PseudoRandom prng, ClassNode cn, MethodNode mn) {
            super(api, analyzer);
            this.targetIindex = targetIindex;
            this.prng = prng;
            this.cn = cn;
            this.mn = mn;
            this.analyzer = analyzer;
        }

        private void insertLDC() {
            switch(prng.closedRange(1,4)){
                case 1 -> {
                    // INT
                    this.mv.visitLdcInsn(prng.closedRange(Integer.MIN_VALUE, Integer.MAX_VALUE));
                    this.mv.visitInsn(Opcodes.POP);
                }
                case 2 -> {
                    // LONG
                    this.mv.visitLdcInsn(prng.nextLong());
                    this.mv.visitInsn(Opcodes.POP2);
                }
                case 3 -> {
                    // FLOAT
                    this.mv.visitLdcInsn(prng.closedRange(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY));
                    this.mv.visitInsn(Opcodes.POP);
                }
                case 4 -> {
                    // DOUBLE
                    this.mv.visitLdcInsn(prng.closedRange(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY));
                    this.mv.visitInsn(Opcodes.POP2);
                }
            }
        }

        private void insertLoad() {
            int localIdx = prng.indexIn(analyzer.locals);
            if (analyzer.locals.get(localIdx) == Opcodes.TOP && localIdx > 0) {
                localIdx--;
            }

            Object typeObj = analyzer.locals.get(localIdx);

            if (typeObj instanceof String s) {
                this.mv.visitVarInsn(Opcodes.ALOAD, localIdx);
                this.mv.visitInsn(Opcodes.POP);
            }
            else if (typeObj instanceof Integer type) {
                if (type == Opcodes.INTEGER) {
                    this.mv.visitVarInsn(Opcodes.ILOAD, localIdx);
                    this.mv.visitInsn(Opcodes.POP);
                }
                else if (type == Opcodes.FLOAT) {
                    this.mv.visitVarInsn(Opcodes.FLOAD, localIdx);
                    this.mv.visitInsn(Opcodes.POP);
                }
                else if (type == Opcodes.LONG) {
                    this.mv.visitVarInsn(Opcodes.LLOAD, localIdx);
                    this.mv.visitInsn(Opcodes.POP2);
                }
                else if (type == Opcodes.DOUBLE) {
                    this.mv.visitVarInsn(Opcodes.DLOAD, localIdx);
                    this.mv.visitInsn(Opcodes.POP2);
                }
                else if (type == Opcodes.NULL) {
                    this.mv.visitInsn(Opcodes.ACONST_NULL);
                    this.mv.visitInsn(Opcodes.POP);
                }
                else {
                    // TODO: remove throw
                    System.err.println("Selected local var with unimplemented type! " + analyzer.locals.get(localIdx));
                    throw new RuntimeException("Selected local var with unimplemented type! " + analyzer.locals.get(localIdx));
                }
            }
            else {
                System.err.println("Selected local var with unimplemented type! " + analyzer.locals.get(localIdx));
                throw new RuntimeException("Selected local var with unimplemented type! " + analyzer.locals.get(localIdx));
            }
        }

        private void insertGetField() {
            List<FieldNode> fields = cn.fields;
            if ((mn.access & Opcodes.ACC_STATIC) != 0) {
                fields = fields.stream().filter(f -> (f.access & Opcodes.ACC_STATIC) != 0).collect(Collectors.toList());
            }
            FieldNode fn = prng.pickIn(fields);

            if ((fn.access & Opcodes.ACC_STATIC) != 0) {
                this.mv.visitFieldInsn(Opcodes.GETSTATIC, cn.name, fn.name, fn.desc);
            }
            else {
                this.mv.visitVarInsn(Opcodes.ALOAD, 0); // this
                this.mv.visitFieldInsn(Opcodes.GETFIELD, cn.name, fn.name, fn.desc);
            }

            if (fn.desc.equals(Type.DOUBLE_TYPE.toString()) || fn.desc.equals(Type.LONG_TYPE.toString())) {
                this.mv.visitInsn(Opcodes.POP2);    
            }
            else {
                this.mv.visitInsn(Opcodes.POP);
            }
        }

        private List<Runnable> gatherVariants() {
            List<Runnable> res = new ArrayList<>();
            res.add(this::insertLDC);
            if (analyzer.locals.size() > 0) {
                res.add(this::insertLoad);
            }
            if (cn.fields.size() > 0) {
                res.add(this::insertGetField);
            }
            return res;
        }

        private void insertDeadCode() {
            prng.pickIn(gatherVariants()).run();
        }

        @Override
        public void visitInstruction() {
            if (iindex() == targetIindex) {
                insertDeadCode();
            }
        }
    }
}