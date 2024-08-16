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
import jdk.graal.compiler.test.bytecodefuzz.FieldHolder;

public abstract class InsertValuePushMethodVisitor extends InstructionVisitor {

    protected final int targetIindex;
    protected final PseudoRandom prng;
    protected final ClassNode cn;
    protected final MethodNode mn;
    protected final AnalyzerAdapter analyzer;
    private Map<Type, Consumer<Type>> freshValueInserters = null;

    public final static int MIN_ARRAY_SIZE = 10;
    public final static int MAX_ARRAY_SIZE = 100;
    
    // Expected number of derefs = 0.5 (would be 0.33 for 4 and 1 for 2)
    public final static int RECURSIVE_DEREF_INVERSE_FREQ = 3;

    public InsertValuePushMethodVisitor(int api, AnalyzerAdapter analyzer, int targetIindex, PseudoRandom prng, ClassNode cn, MethodNode mn) {
        super(api, analyzer);
        this.targetIindex = targetIindex;
        this.prng = prng;
        this.cn = cn;
        this.mn = mn;
        this.analyzer = analyzer;
    }

    private void newObj(Type type) {
        this.mv.visitTypeInsn(Opcodes.NEW, AsmTypeSupport.objectType.getInternalName());
        this.mv.visitInsn(Opcodes.DUP);
        this.mv.visitMethodInsn(Opcodes.INVOKESPECIAL, AsmTypeSupport.objectType.getInternalName(), "<init>", Type.getMethodDescriptor(Type.VOID_TYPE), false);
        afterPush(AsmTypeSupport.objectType);
    }

    private void newArray(Type arrayType, Type targetType) {
        int length = prng.closedRange(MIN_ARRAY_SIZE, MAX_ARRAY_SIZE);
        this.mv.visitIntInsn(Opcodes.BIPUSH, length);

        if (arrayType.equals(AsmTypeSupport.objectArrayType)) {
            this.mv.visitTypeInsn(Opcodes.ANEWARRAY, arrayType.getInternalName());
        }
        else {
            this.mv.visitIntInsn(Opcodes.NEWARRAY, AsmTypeSupport.getPrimitiveArrayTypeOpcode(arrayType));
        }
        afterPush(tryInsertDeref(arrayType, targetType));
    }

    private void newFieldHolder(Type type) {
        this.mv.visitTypeInsn(Opcodes.NEW, AsmTypeSupport.fieldHolderType.getInternalName());
        this.mv.visitInsn(Opcodes.DUP);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, AsmTypeSupport.fieldHolderType.getInternalName(), "<init>", Type.getMethodDescriptor(Type.VOID_TYPE), false);
        afterPush(tryInsertDeref(AsmTypeSupport.fieldHolderType, type));
    }

    private Map<Type, Consumer<Type>> getFreshValueInserters() {
        if (freshValueInserters == null) {
            Map<Type, Consumer<Type>> map = new HashMap<>();
            map.put(Type.INT_TYPE, t -> {this.mv.visitLdcInsn(this.prng.closedRange(Integer.MIN_VALUE, Integer.MAX_VALUE)); afterPush(Type.INT_TYPE);});
            map.put(Type.LONG_TYPE, t -> {this.mv.visitLdcInsn(this.prng.nextLong()); afterPush(Type.LONG_TYPE);});
            map.put(Type.FLOAT_TYPE, t -> {this.mv.visitLdcInsn(this.prng.closedRange(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY)); afterPush(Type.FLOAT_TYPE);});
            map.put(Type.DOUBLE_TYPE, t -> {this.mv.visitLdcInsn(this.prng.closedRange(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY)); afterPush(Type.DOUBLE_TYPE);});
            map.put(AsmTypeSupport.objectType, this::newObj);
            map.put(AsmTypeSupport.intArrayType, t -> newArray(AsmTypeSupport.intArrayType, t));
            map.put(AsmTypeSupport.floatArrayType, t -> newArray(AsmTypeSupport.floatArrayType, t));
            map.put(AsmTypeSupport.longArrayType, t -> newArray(AsmTypeSupport.longArrayType, t));
            map.put(AsmTypeSupport.doubleArrayType, t -> newArray(AsmTypeSupport.doubleArrayType, t));
            map.put(AsmTypeSupport.objectArrayType, t -> newArray(AsmTypeSupport.objectArrayType, t));
            map.put(AsmTypeSupport.fieldHolderType, this::newFieldHolder);
            freshValueInserters = map;
        }
        return freshValueInserters;
    }

    // called after the Push, with the type of the pushed value
    protected abstract void afterPush(Type type);

    private Type tryInsertFieldHolderDeref(Type targetType) {
        
        if (AsmTypeSupport.canBeAssignedTo(AsmTypeSupport.fieldHolderType, targetType)) {
            if (!prng.trueInOneOutOf(RECURSIVE_DEREF_INVERSE_FREQ)) {
                return AsmTypeSupport.fieldHolderType;
            }
        }

        List<Type> fieldTypes = AsmTypeSupport.fieldHolderFieldTypes.stream()
            .filter(ft -> AsmTypeSupport.canBeDereferencedTo(ft, targetType))
            .collect(Collectors.toList());
            
        Type fieldType = prng.pickIn(fieldTypes);

        mv.visitFieldInsn(
            Opcodes.GETFIELD,
            AsmTypeSupport.fieldHolderType.getInternalName(),
            AsmTypeSupport.fieldHolderFieldNameByType.get(fieldType),
            fieldType.getDescriptor()
        );
        return tryInsertDeref(fieldType, targetType);
    }

    private Type tryInsertArrayDeref(Type currentType, Type targetType) {
        assert(currentType.getSort() == Type.ARRAY);

        if (currentType.equals(targetType)) {
            return currentType;
        }

        // If we cannot assign the result of a deref, don't deref
        if (!AsmTypeSupport.canBeDereferencedToStrict(currentType, targetType)) {
            return currentType;
        }

        if (AsmTypeSupport.canBeAssignedTo(currentType, targetType)) {
            if (!prng.trueInOneOutOf(RECURSIVE_DEREF_INVERSE_FREQ)) {
                return currentType;
            }
        }
        else {
            assert(AsmTypeSupport.isElementTypeOf(currentType, targetType) || AsmTypeSupport.isSubarrayTypeOf(currentType, targetType));
        }
        
        // Try to minimize null deref by always dereferencing indices valid in freshly inserted arrays
        int index = prng.indexIn(MIN_ARRAY_SIZE);
        mv.visitIntInsn(Opcodes.BIPUSH, index);
        Type elementType = currentType.getElementType();
        mv.visitInsn(AsmTypeSupport.getArayDerefOpcode(currentType));
        // getStackTosType() works because the analyzer already visited ^this just added instruction
        return tryInsertDeref(getStackTosType(), targetType);
    }

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

        List<Type> validFreshTypes = getFreshValueInserters().keySet().stream()
            .filter(ft -> AsmTypeSupport.canBeDereferencedTo(ft, type))
            .collect(Collectors.toList());

        getFreshValueInserters().get(prng.pickIn(validFreshTypes)).accept(type);
    }

    private void insertLoad(Type type) {

        List<Integer> validIndices = IntStream
            .range(0, analyzer.locals.size())
            .filter(i -> AsmTypeSupport.canBeDereferencedTo(AsmTypeSupport.getType(analyzer.locals.get(i)), type))
            .boxed()
            .collect(Collectors.toList());
        
        int localIdx = prng.pickIn(validIndices);
        Type selectedType = AsmTypeSupport.getType(analyzer.locals.get(localIdx));

        this.mv.visitVarInsn(selectedType.getOpcode(Opcodes.ILOAD), localIdx);
        afterPush(tryInsertDeref(selectedType, type));
    }

    private void insertGetField(Type type) {
        List<FieldNode> fields = cn.fields;

        if ((mn.access & Opcodes.ACC_STATIC) != 0) {
            fields = fields.stream().filter(f -> (f.access & Opcodes.ACC_STATIC) != 0).collect(Collectors.toList());
        }

        fields = fields.stream().filter(f -> AsmTypeSupport.canBeDereferencedTo(Type.getType(f.desc), type)).collect(Collectors.toList());

        FieldNode fn = prng.pickIn(fields);

        if ((fn.access & Opcodes.ACC_STATIC) != 0) {
            this.mv.visitFieldInsn(Opcodes.GETSTATIC, cn.name, fn.name, fn.desc);
        }
        else {
            this.mv.visitVarInsn(Opcodes.ALOAD, 0); // this
            this.mv.visitFieldInsn(Opcodes.GETFIELD, cn.name, fn.name, fn.desc);
        }
        afterPush(tryInsertDeref(Type.getType(fn.desc), type));
    }

    private void insertUseTos(Type type) {
        int stackSize = analyzer.stack.size();
        Object stackLastTypeObj = analyzer.stack.get(stackSize - 1);
        this.mv.visitInsn(stackLastTypeObj.equals(Opcodes.TOP) ? Opcodes.DUP2 : Opcodes.DUP);
        afterPush(tryInsertDeref(getStackTosType(), type));
    }

    private boolean canGenerateFresh(Type type) {
        return getFreshValueInserters().keySet().stream()
            .filter(ft -> AsmTypeSupport.canBeDereferencedTo(ft, type))
            .count() > 0;
    }

    private boolean canLoad(Type type) {
        return analyzer.locals.stream()
            .filter(l -> AsmTypeSupport.canBeDereferencedTo(AsmTypeSupport.getType(l), type))
            .count() > 0;
    }

    private boolean canGetField(Type type) {
        return cn.fields.stream()
            .filter(f -> AsmTypeSupport.canBeDereferencedTo(Type.getType(f.desc), type))
            .count() > 0;
    }

    private Type getStackTosType() {
        if (analyzer.stack == null) return null;
        int stackSize = analyzer.stack.size();
        if (stackSize == 0) return null;

        Object stackLastTypeObj = analyzer.stack.get(stackSize - 1);
        if (stackLastTypeObj.equals(Opcodes.TOP)) {
            stackLastTypeObj = analyzer.stack.get(stackSize - 2);
        }
        // TOS is uninitialized
        if (stackLastTypeObj instanceof Label) {
            return null;
        }
        return AsmTypeSupport.getType(stackLastTypeObj);
    }

    private boolean canUseTOS(Type type) {
        // getStackTosType returns null on unknown and uninitialized types, empty or null stack and such
        return AsmTypeSupport.canBeDereferencedTo(getStackTosType(), type);
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
        if (canUseTOS(type)) {
            res.add(this::insertUseTos);
        }
        assert(!res.isEmpty());
        return res;
    }

    /** Pushes a random value on TOS - either a fresh value, duplicate of TOS, a loaded local variable or field (+- dereferecing arrays) */
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