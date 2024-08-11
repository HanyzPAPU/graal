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
import jdk.graal.compiler.test.bytecodefuzz.FieldHolder;

public abstract class InsertValuePushMethodVisitor extends InstructionVisitor {

    private final int targetIindex;
    private final PseudoRandom prng;
    private final ClassNode cn;
    private final MethodNode mn;
    private final AnalyzerAdapter analyzer;
    private final Runnable[] constantInserters;

    public final static int MIN_ARRAY_SIZE = 10;
    public final static int MAX_ARRAY_SIZE = 100;

    public InsertValuePushMethodVisitor(int api, AnalyzerAdapter analyzer, int targetIindex, PseudoRandom prng, ClassNode cn, MethodNode mn) {
        super(api, analyzer);
        this.targetIindex = targetIindex;
        this.prng = prng;
        this.cn = cn;
        this.mn = mn;
        this.analyzer = analyzer;
        this.constantInserters = new Runnable[] {
            () -> {this.mv.visitLdcInsn(this.prng.closedRange(Integer.MIN_VALUE, Integer.MAX_VALUE)); afterPush(Type.INT_TYPE);}, // INT
            () -> {this.mv.visitLdcInsn(this.prng.nextLong()); afterPush(Type.LONG_TYPE);}, // LONG
            () -> {this.mv.visitLdcInsn(this.prng.closedRange(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY)); afterPush(Type.FLOAT_TYPE);}, // FLOAT
            () -> {this.mv.visitLdcInsn(this.prng.closedRange(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY)); afterPush(Type.DOUBLE_TYPE);} // DOUBLE
            // TODO: generate new object, new array, new String and new FieldHolder!
        };
    }

    // called after the Push, with the type of the pushed value
    protected abstract void afterPush(Type type);

    private Type tryInsertFieldHolderDeref() {
        if (prng.choice()) return Type.getType(FieldHolder.class); // 50 % chance to not deref
        
        Field field = prng.pickIn(FieldHolder.class.getFields());
        mv.visitFieldInsn(Opcodes.GETFIELD, Type.getInternalName(FieldHodler.class), field.getName(), Type.getDescriptor(field.getType()));
        return tryInsertDeref(Type.getType(field.getType()));
    }

    private Type tryInsertArrayDeref(Type type) {
        if (prng.choice()) return type; // 50 % chance to not deref

        // Try to minimize null deref by always dereferencing indices valid in inserted arrays
        int index = prng.indexIn(MIN_ARRAY_SIZE);
        mv.visitIntInsn(Opcodes.BIPUSH, index);
        mv.visitInsn(type.getOpcode(Opcodes.IALOAD));
        return tryInsertDeref(type.getElementType());
    }

    private Type tryInsertDeref(Type type) {
        // TODO: FieldHolder and arrays -> possible to add load/getfield from them (which we can, but do not have to do)
        // We can even try to do recursion here XD
        if(type.equals(Type.getType(FieldHolder.class))) {
            return tryInsertFieldHolderDeref();
        }
        if (type.getSort() == Type.ARRAY) {
            return tryInsertArrayDeref(type);
        }
        return type;
    }

    private void insertLDC() {
        prng.pickIn(constantInserters).run();        
    }

    private void insertLoad() {
        int localIdx = prng.indexIn(analyzer.locals);
        if (analyzer.locals.get(localIdx) == Opcodes.TOP && localIdx > 0) {
            localIdx--;
        }

        Object typeObj = analyzer.locals.get(localIdx);

        if (typeObj instanceof String s) {
            this.mv.visitVarInsn(Opcodes.ALOAD, localIdx);
            afterPush(tryInsertDeref(Type.getObjectType(s)));
        }
        else if (typeObj instanceof Integer type) {
            if (type == Opcodes.INTEGER) {
                this.mv.visitVarInsn(Opcodes.ILOAD, localIdx);
                afterPush(Type.INT_TYPE);
            }
            else if (type == Opcodes.FLOAT) {
                this.mv.visitVarInsn(Opcodes.FLOAD, localIdx);
                afterPush(Type.FLOAT_TYPE);
            }
            else if (type == Opcodes.LONG) {
                this.mv.visitVarInsn(Opcodes.LLOAD, localIdx);
                afterPush(Type.LONG_TYPE);
            }
            else if (type == Opcodes.DOUBLE) {
                this.mv.visitVarInsn(Opcodes.DLOAD, localIdx);
                afterPush(Type.DOUBLE_TYPE);
            }
            else if (type == Opcodes.NULL) {
                this.mv.visitInsn(Opcodes.ACONST_NULL);
                afterPush(Type.getType(Object.class));
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
        afterPush(tryInsertDeref(Type.getType(fn.desc)));
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