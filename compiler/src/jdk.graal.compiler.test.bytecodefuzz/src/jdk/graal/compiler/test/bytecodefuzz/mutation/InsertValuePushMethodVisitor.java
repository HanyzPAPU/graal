package jdk.graal.compiler.test.bytecodefuzz.mutation;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.lang.reflect.Field;

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

    // TODO: remove the targetting?
    private final int targetIindex;
    private final PseudoRandom prng;
    private final ClassNode cn;
    private final MethodNode mn;
    private final AnalyzerAdapter analyzer;
    private Map<Type, Runnable> freshValueInserters = null;

    public final static int MIN_ARRAY_SIZE = 10;
    public final static int MAX_ARRAY_SIZE = 100;

    private static final Type objectType = Type.getType(Object.class);
    private static final Type intArrayType = Type.getType(int[].class);
    private static final Type floatArrayType = Type.getType(float[].class);
    private static final Type longArrayType = Type.getType(long[].class);
    private static final Type doubleArrayType = Type.getType(double[].class);
    private static final Type objectArrayType = Type.getType(Object[].class);
    private static final Type fieldHolderType = Type.getType(FieldHolder.class);

    public InsertValuePushMethodVisitor(int api, AnalyzerAdapter analyzer, int targetIindex, PseudoRandom prng, ClassNode cn, MethodNode mn) {
        super(api, analyzer);
        this.targetIindex = targetIindex;
        this.prng = prng;
        this.cn = cn;
        this.mn = mn;
        this.analyzer = analyzer;
    }

    private void newObj() {
        this.mv.visitTypeInsn(Opcodes.NEW, objectType.getInternalName());
        this.mv.visitInsn(Opcodes.DUP);
        this.mv.visitMethodInsn(Opcodes.INVOKESPECIAL, objectType.getInternalName(), "<init>", Type.getMethodDescriptor(Type.VOID_TYPE), false);
        afterPush(objectType);
    }

    private void newArray(Type type) {
        int length = prng.closedRange(MIN_ARRAY_SIZE, MAX_ARRAY_SIZE);
        this.mv.visitIntInsn(Opcodes.BIPUSH, length);
        this.mv.visitTypeInsn(Opcodes.ANEWARRAY, type.getInternalName());
        afterPush(type);
    }

    private void newFieldHolder() {
        this.mv.visitTypeInsn(Opcodes.NEW, fieldHolderType.getInternalName());
        this.mv.visitInsn(Opcodes.DUP);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, fieldHolderType.getInternalName(), "<init>", Type.getMethodDescriptor(Type.VOID_TYPE), false);
        afterPush(fieldHolderType);
    }

    private Map<Type, Runnable> getFreshValueInserters() {
        if (freshValueInserters == null) {
            Map<Type, Runnable> map = new HashMap<>();
            map.put(Type.INT_TYPE, () -> {this.mv.visitLdcInsn(this.prng.closedRange(Integer.MIN_VALUE, Integer.MAX_VALUE)); afterPush(Type.INT_TYPE);});
            map.put(Type.LONG_TYPE, () -> {this.mv.visitLdcInsn(this.prng.nextLong()); afterPush(Type.LONG_TYPE);});
            map.put(Type.FLOAT_TYPE, () -> {this.mv.visitLdcInsn(this.prng.closedRange(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY)); afterPush(Type.FLOAT_TYPE);});
            map.put(Type.DOUBLE_TYPE, () -> {this.mv.visitLdcInsn(this.prng.closedRange(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY)); afterPush(Type.DOUBLE_TYPE);});
            map.put(objectType, this::newObj);
            map.put(intArrayType, () -> newArray(intArrayType));
            map.put(floatArrayType, () -> newArray(floatArrayType));
            map.put(longArrayType, () -> newArray(longArrayType));
            map.put(doubleArrayType, () -> newArray(doubleArrayType));
            map.put(objectArrayType, () -> newArray(objectArrayType));
            map.put(fieldHolderType, this::newFieldHolder);
            freshValueInserters = map;
        }
        return freshValueInserters;
    }

    // called after the Push, with the type of the pushed value
    protected abstract void afterPush(Type type);

    private Type tryInsertFieldHolderDeref() {
        if (prng.choice()) return fieldHolderType; // 50 % chance to not deref

        Field field = prng.pickIn(FieldHolder.class.getFields());
        mv.visitFieldInsn(Opcodes.GETFIELD, Type.getInternalName(FieldHolder.class), field.getName(), Type.getDescriptor(field.getType()));
        return tryInsertDeref(Type.getType(field.getType()));
    }

    private Type tryInsertArrayDeref(Type type) {
        if (prng.choice()) return type; // 50 % chance to not deref

        // Try to minimize null deref by always dereferencing indices valid in inserted arrays
        int index = prng.indexIn(MIN_ARRAY_SIZE);
        mv.visitIntInsn(Opcodes.BIPUSH, index);
        Type elementType = type.getElementType();
        mv.visitInsn(elementType.getOpcode(Opcodes.IALOAD));
        return tryInsertDeref(elementType);
    }

    private Type tryInsertDeref(Type type) {
        if(type.equals(fieldHolderType)) {
            return tryInsertFieldHolderDeref();
        }
        if (type.getSort() == Type.ARRAY) {
            return tryInsertArrayDeref(type);
        }
        return type;
    }

    /** Fresh is either a constant for primitive types or a new value for reference types */
    private void insertFresh(Type type) {
        if (type == null) {
            prng.pickIn(new ArrayList<Runnable>(getFreshValueInserters().values())).run();        
        }
        else {
            getFreshValueInserters().get(type).run();
        }
    }

    private void insertLoad(Type type) {
        int localIdx = -1;
        Type selectedType = type;
        if (selectedType == null) {
            List<Integer> validIndices = IntStream
                .range(0, analyzer.locals.size())
                .filter(i -> AsmTypeSupport.getType(analyzer.locals.get(i)) != null)
                .boxed()
                .collect(Collectors.toList());
            localIdx = prng.pickIn(validIndices);
            selectedType = AsmTypeSupport.getType(analyzer.locals.get(localIdx));
        }
        else {
            List<Integer> validIndices = IntStream
                .range(0, analyzer.locals.size())
                .filter(i -> type.equals(AsmTypeSupport.getType(analyzer.locals.get(i))))
                .boxed()
                .collect(Collectors.toList());
            assert(validIndices.size() > 0);
            localIdx = prng.pickIn(validIndices);
            assert(type.equals(AsmTypeSupport.getType(analyzer.locals.get(localIdx))));
        }

        this.mv.visitVarInsn(selectedType.getOpcode(Opcodes.ILOAD), localIdx);
        afterPush(selectedType);
    }

    private void insertGetField(Type type) {
        List<FieldNode> fields = cn.fields;

        if ((mn.access & Opcodes.ACC_STATIC) != 0) {
            fields = fields.stream().filter(f -> (f.access & Opcodes.ACC_STATIC) != 0).collect(Collectors.toList());
        }

        if (type != null) {
            fields = fields.stream().filter(f -> Type.getType(f.desc).equals(type)).collect(Collectors.toList());
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

    private boolean canGenerateFresh(Type type) {
        return type == null || getFreshValueInserters().containsKey(type);
    }

    private boolean canLoad(Type type) {
        boolean hasAnyLocals = analyzer.locals.stream().map(AsmTypeSupport::getType).filter(x -> x != null).count() > 0;
        boolean hasTypedLocals = (type != null) && (analyzer.locals.stream().filter(l -> type.equals(AsmTypeSupport.getType(l))).count() > 0);
        return (type == null && hasAnyLocals) || hasTypedLocals;
    }

    private boolean canGetField(Type type) {
        boolean hasAnyFields = cn.fields.size() > 0;
        boolean hasTypedFields = (type != null) && (cn.fields.stream().filter(f -> type.equals(Type.getType(f.desc))).count() > 0);
        return (type == null && hasAnyFields) || hasTypedFields;
    }

    private List<Consumer<Type>> gatherVariants(Type type) {
        List<Consumer<Type>> res = new ArrayList<>();
        if (canGenerateFresh(type)) {
            res.add(this::insertFresh);
        }
        if (canLoad(type)) {
            res.add(this::insertLoad);
        }
        if (canGetField(type)) {
            res.add(this::insertGetField);
        }
        assert(!res.isEmpty());
        return res;
    }

    /** Pushes a random value on TOS - either a fresh value, a loaded local variable or field */
    protected final void pushValue(Type type) {
        prng.pickIn(gatherVariants(type)).accept(type);
    }

    protected final void pushValue() {
        pushValue(null);
    }

    @Override
    public void visitInstruction() {
        if (iindex() == targetIindex) {
            pushValue();
        }
    }
}