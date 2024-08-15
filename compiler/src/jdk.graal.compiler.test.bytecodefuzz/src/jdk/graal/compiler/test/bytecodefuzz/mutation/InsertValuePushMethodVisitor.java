package jdk.graal.compiler.test.bytecodefuzz.mutation;

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
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AnalyzerAdapter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.Type;

import com.code_intelligence.jazzer.mutation.api.PseudoRandom;
import jdk.graal.compiler.test.bytecodefuzz.FieldHolder;

public abstract class InsertValuePushMethodVisitor extends InstructionVisitor {

    protected final int targetIindex;
    protected final PseudoRandom prng;
    protected final ClassNode cn;
    protected final MethodNode mn;
    protected final AnalyzerAdapter analyzer;
    private Map<Type, Runnable> freshValueInserters = null;

    public final static int MIN_ARRAY_SIZE = 10;
    public final static int MAX_ARRAY_SIZE = 100;

    
    public InsertValuePushMethodVisitor(int api, AnalyzerAdapter analyzer, int targetIindex, PseudoRandom prng, ClassNode cn, MethodNode mn) {
        super(api, analyzer);
        this.targetIindex = targetIindex;
        this.prng = prng;
        this.cn = cn;
        this.mn = mn;
        this.analyzer = analyzer;
    }

    private void newObj() {
        this.mv.visitTypeInsn(Opcodes.NEW, AsmTypeSupport.objectType.getInternalName());
        this.mv.visitInsn(Opcodes.DUP);
        this.mv.visitMethodInsn(Opcodes.INVOKESPECIAL, AsmTypeSupport.objectType.getInternalName(), "<init>", Type.getMethodDescriptor(Type.VOID_TYPE), false);
        afterPush(AsmTypeSupport.objectType);
    }

    private void newArray(Type type) {
        int length = prng.closedRange(MIN_ARRAY_SIZE, MAX_ARRAY_SIZE);
        this.mv.visitIntInsn(Opcodes.BIPUSH, length);
        this.mv.visitTypeInsn(Opcodes.ANEWARRAY, type.getInternalName());
        afterPush(type);
    }

    private void newFieldHolder() {
        this.mv.visitTypeInsn(Opcodes.NEW, AsmTypeSupport.fieldHolderType.getInternalName());
        this.mv.visitInsn(Opcodes.DUP);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, AsmTypeSupport.fieldHolderType.getInternalName(), "<init>", Type.getMethodDescriptor(Type.VOID_TYPE), false);
        afterPush(AsmTypeSupport.fieldHolderType);
    }

    private Map<Type, Runnable> getFreshValueInserters() {
        if (freshValueInserters == null) {
            Map<Type, Runnable> map = new HashMap<>();
            map.put(Type.INT_TYPE, () -> {this.mv.visitLdcInsn(this.prng.closedRange(Integer.MIN_VALUE, Integer.MAX_VALUE)); afterPush(Type.INT_TYPE);});
            map.put(Type.LONG_TYPE, () -> {this.mv.visitLdcInsn(this.prng.nextLong()); afterPush(Type.LONG_TYPE);});
            map.put(Type.FLOAT_TYPE, () -> {this.mv.visitLdcInsn(this.prng.closedRange(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY)); afterPush(Type.FLOAT_TYPE);});
            map.put(Type.DOUBLE_TYPE, () -> {this.mv.visitLdcInsn(this.prng.closedRange(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY)); afterPush(Type.DOUBLE_TYPE);});
            map.put(AsmTypeSupport.objectType, this::newObj);
            map.put(AsmTypeSupport.intArrayType, () -> newArray(AsmTypeSupport.intArrayType));
            map.put(AsmTypeSupport.floatArrayType, () -> newArray(AsmTypeSupport.floatArrayType));
            map.put(AsmTypeSupport.longArrayType, () -> newArray(AsmTypeSupport.longArrayType));
            map.put(AsmTypeSupport.doubleArrayType, () -> newArray(AsmTypeSupport.doubleArrayType));
            map.put(AsmTypeSupport.objectArrayType, () -> newArray(AsmTypeSupport.objectArrayType));
            map.put(AsmTypeSupport.fieldHolderType, this::newFieldHolder);
            freshValueInserters = map;
        }
        return freshValueInserters;
    }

    // called after the Push, with the type of the pushed value
    protected abstract void afterPush(Type type);

    private Type tryInsertFieldHolderDeref(Type targetType) {
        Field field;

        if (targetType != null) {
            List<Field> fields = new ArrayList<>(FieldHolder.class.getFields()).stream()
                .filter(f -> targetType.equals(Type.getType(f.getType())))
                .collect(Collectors.toList());
            
            field = prng.pickIn(fields);

            if (fieldType.equals(AsmTypeSupport.fieldHolderType) && prng.choice()) {
                // If the target type is FieldHolder, 50 % chance to not deref
                return targetType; // == fieldType == AsmTypeSupport.fieldHolderType
            }
            // Else: always deref to the correct field
        }
        // targetType == null
        else if (prng.choice()) {
            return AsmTypeSupport.fieldHolderType; // 50 % chance to not deref
        }
        else {
            field = prng.pickIn(FieldHolder.class.getFields());
        }
        
        Type fieldType = Type.getType(field.getType());
        assert(targetType == null || targetType.equals(fieldType));

        mv.visitFieldInsn(Opcodes.GETFIELD, AsmTypeSupport.fieldHolderType.getInternalName(), field.getName(), fieldType.getDescriptor());
        return tryInsertDeref(fieldType, targetType);
    }

    private Type tryInsertArrayDeref(Type currentType, Type targetType) {

        if (currentType.equals(targetType)) {
            assert (targetType.getSort() == Type.ARRAY);
            return currentType;
        }
        
        if ((targetType != null && targetType.getSort() != Type.ARRAY) || prng.choice()) {

            assert(target == null || currentType.getElementType().equals(targetType));
            // Try to minimize null deref by always dereferencing indices valid in freshly inserted arrays
            int index = prng.indexIn(MIN_ARRAY_SIZE);
            mv.visitIntInsn(Opcodes.BIPUSH, index);
            Type elementType = type.getElementType();
            mv.visitInsn(elementType.getOpcode(Opcodes.IALOAD));
            return tryInsertDeref(elementType);
        }
        
        assert(targetType == null);
        return type; // 50 % chance to not deref
    }

    // TODO: what about target type?
    private Type tryInsertDeref(Type currentType, Type targetType) {

        if (currentType.equals(targetType)) {
            if(currentType.equals(AsmTypeSupport.fieldHolderType)) {
                return tryInsertFieldHolderDeref(targetType);
            }
        }
        else {
            assert(targetType == null || currentType.equals(AsmTypeSupport.fieldHolderType) || currentType.getElementType().equals(targetType)); 
            if(currentType.equals(AsmTypeSupport.fieldHolderType)) {
                return tryInsertFieldHolderDeref(targetType);
            }
            if (currentType.getSort() == Type.ARRAY) {
                return tryInsertArrayDeref(currentType, targetType);
            }
        }
        assert(targetType == null || currentType.equals(targetType));
        return currentType;
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

    private void insertUseTos(Type type) {
        int stackSize = analyzer.stack.size();
        Object stackLastTypeObj = analyzer.stack.get(stackSize - 1);
        this.mv.visitInsn(stackLastTypeObj.equals(Opcodes.TOP) ? Opcodes.DUP2 : Opcodes.DUP);
        afterPush(tryInsertDeref(getStackTosType()));
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

    private Type getStackTosType() {
        int stackSize = analyzer.stack.size();
        Object stackLastTypeObj = analyzer.stack.get(stackSize - 1);
        return AsmTypeSupport.getType(stackLastTypeObj.equals(Opcodes.TOP) ? analyzer.stack.get(stackSize - 2) : stackLastTypeObj);
    }

    private boolean canUseTOS(Type type) {
        boolean stackNotEmpty = analyzer.stack.size() > 0;
        boolean hasTypedTos = type != null && type.equals(getStackTosType());
        return (type == null && stackNotEmpty) || hasTypedTos;
    }

    private List<Consumer<Type>> gatherVariants(Type type) {
        List<Consumer<Type>> res = new ArrayList<>();
        // if (canGenerateFresh(type)) {
        //     res.add(this::insertFresh);
        // }
        // if (canLoad(type)) {
        //     res.add(this::insertLoad);
        // }
        // if (canGetField(type)) {
        //     res.add(this::insertGetField);
        // }
        if (canUseTOS(type)) {
            res.add(this::insertUseTos);
        }
        assert(!res.isEmpty());
        return res;
    }

    /** Pushes a random value on TOS - either a fresh value, a loaded local variable or field */
    protected final void pushValue(Type type) {
        // TODO: strict type equivalence is not necessary, subclass can also work?
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