package jdk.graal.compiler.test.bytecodefuzz.mutation;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import jdk.graal.compiler.test.bytecodefuzz.FieldHolder;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MultiANewArrayInsnNode;

public final class AsmTypeSupport {

    public static final Type objectType = Type.getType(Object.class);
    public static final Type stringType = Type.getType(String.class);
    
    public static final Type intArrayType = Type.getType(int[].class);
    public static final Type floatArrayType = Type.getType(float[].class);
    public static final Type longArrayType = Type.getType(long[].class);
    public static final Type doubleArrayType = Type.getType(double[].class);
    public static final Type objectArrayType = Type.getType(Object[].class);

    public static final Type fieldHolderType = Type.getType(FieldHolder.class);
    public static final List<Type> fieldHolderFieldTypes = Arrays.stream(FieldHolder.class.getFields()).map(f -> Type.getType(f.getType())).collect(Collectors.toList());
    public static final Map<Type, String> fieldHolderFieldNameByType = Arrays.stream(FieldHolder.class.getFields())
        .collect(Collectors.toMap(
            f -> Type.getType(f.getType()), // Key mapper
            f -> f.getName()                // Val mapper
        ));

    public static Type getType(Object type) {
        if (type instanceof Integer simple) {
            if (simple == Opcodes.INTEGER) {
                return Type.INT_TYPE;
            }
            if (simple == Opcodes.LONG) {
                return Type.LONG_TYPE;
            }
            if (simple == Opcodes.FLOAT) {
                return Type.FLOAT_TYPE;
            }
            if (simple == Opcodes.DOUBLE) {
                return Type.DOUBLE_TYPE;
            }
            if (simple == Opcodes.NULL) {
                return Type.getType(Object.class);
            }
        }
        if (type instanceof String typeString) {
            return Type.getObjectType(typeString);
        }
        return null;
    }

    public static boolean isElementTypeOf(Type arrayType, Type elementType) {
        return arrayType.getElementType().equals(elementType);
    }

    public static boolean isSubarrayTypeOf(Type superArrayType, Type subArrayType) {
        if (subArrayType.getSort() != Type.ARRAY) return false;
        return superArrayType.getElementType().equals(subArrayType.getElementType()) && superArrayType.getDimensions() > subArrayType.getDimensions();
    }

    public static int getArayDerefOpcode(Type arrayType) {
        return arrayType.getDimensions() > 1 ? Opcodes.AALOAD : arrayType.getElementType().getOpcode(Opcodes.IALOAD);
    }

    public static boolean canBeAssignedTo(Type sourceType, Type targetType) {

        if (sourceType == null) {
            return false;
        }
        
        if (targetType == null) {
            return true;
        }

        int sourceSort = sourceType.getSort();
        // Object and array sort can be accessed as objects
        boolean referenceTypeToObject = targetType.equals(objectType) && (sourceSort == Type.OBJECT || sourceSort == Type.ARRAY);
        boolean sameType = sourceType.equals(targetType);
        //? inheritance?
        return sameType || referenceTypeToObject;
    }

    public static boolean canBeDereferencedToStrict(Type sourceType, Type targetType) {

        // ret <-> targetType can still be reached after one level of deref

        if (sourceType == null) {
            return false;
        }

        if (targetType == null) {
            return true;
        }

        // Array can be dereferenced to subarray or element type or to an objectType if dimensions > 1
        if (sourceType.getSort() == Type.ARRAY) {
            if (sourceType.getDimensions() > 1 && targetType.equals(objectType)) {
                return true;
            }
            return isElementTypeOf(sourceType, targetType) || isSubarrayTypeOf(sourceType, targetType);
        }

        // Field holder can be dereferenced to its fields
        if (sourceType.equals(fieldHolderType)) {
            // Avoid infinite recursion
            if (targetType.equals(fieldHolderType)) return true;
            return fieldHolderFieldTypes.stream().anyMatch(fieldType -> !fieldType.equals(fieldHolderType) && canBeDereferencedTo(fieldType, targetType));
        }

        return false;
    }

    public static boolean canBeDereferencedTo(Type sourceType, Type targetType) {

        if (sourceType == null) {
            return false;
        }

        if (targetType == null) {
            return true;
        }

        // If they can be assigned, they can be dereferenced to each other
        if (canBeAssignedTo(sourceType, targetType)) return true;

        return canBeDereferencedToStrict(sourceType, targetType);
    }

    public static int getPrimitiveArrayTypeOpcode(Type arrayType) {
        switch(arrayType.getElementType().getSort()) {
            case Type.INT -> {return Opcodes.T_INT;}
            case Type.FLOAT -> {return Opcodes.T_FLOAT;}
            case Type.LONG -> {return Opcodes.T_LONG;}
            case Type.DOUBLE -> {return Opcodes.T_DOUBLE;}
        }
        throw new RuntimeException("Unsupported primitive array type " + arrayType);
    }

    // Returns an array type with same element type and 1 less dimensions or the element type if arrayType has only 1 dimension
    public static Type getDirectSubarrayType(Type arrayType) {
        assert(arrayType.getSort() == Type.ARRAY);
        // Array type descriptors always start with [
        return Type.getType(arrayType.getDescriptor().substring(1));
    }

    // Returns the type signature of an instruction as a method type.
    public static Type getInstructionMethodType(AbstractInsnNode insn, List<Object> stack) {

        if (insn.getType() == AbstractInsnNode.METHOD_INSN) {

            MethodInsnNode methodInsnNode = (MethodInsnNode)insn;

            // Ctor descriptor does not match its actual effect on the stack
            if (methodInsnNode.name.equals("<init>")) return null;

            Type methodType = Type.getMethodType(methodInsnNode.desc);

            if (methodInsnNode.getOpcode() == Opcodes.INVOKESTATIC) {
                // Static method call has the signature of the call itself
                return methodType;
            }

            // All other calls must have the `this` argument added to the signature

            List<Type> args = new ArrayList<>(Arrays.asList(methodType.getArgumentTypes()));
            args.add(0, Type.getObjectType(methodInsnNode.owner));
            return Type.getMethodType(methodType.getReturnType(), args.toArray(new Type[0]));
        }

        switch(insn.getOpcode()) {
            case Opcodes.NOP:
                return Type.getMethodType(Type.VOID_TYPE);

            // int(int)
            case Opcodes.INEG:
            case Opcodes.I2B:
            case Opcodes.I2C:
            case Opcodes.I2S:
                return Type.getMethodType(Type.INT_TYPE, Type.INT_TYPE);

            // int()
            case Opcodes.ICONST_M1:
            case Opcodes.ICONST_0:
            case Opcodes.ICONST_1:
            case Opcodes.ICONST_2:
            case Opcodes.ICONST_3:
            case Opcodes.ICONST_4:
            case Opcodes.ICONST_5:
            case Opcodes.BIPUSH:
            case Opcodes.SIPUSH:
                return Type.getMethodType(Type.INT_TYPE);

            // int(int, int)
            case Opcodes.IADD:
            case Opcodes.ISUB:
            case Opcodes.IMUL:
            case Opcodes.IDIV:
            case Opcodes.IREM:
            case Opcodes.IAND:
            case Opcodes.IOR:
            case Opcodes.IXOR:
            case Opcodes.ISHL:
            case Opcodes.ISHR:
            case Opcodes.IUSHR:
                return Type.getMethodType(Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE);

            // long(int)
            case Opcodes.I2L:
                return Type.getMethodType(Type.LONG_TYPE, Type.INT_TYPE);

            // float(int)
            case Opcodes.I2F:
                return Type.getMethodType(Type.FLOAT_TYPE, Type.INT_TYPE);

            // double(int)
            case Opcodes.I2D:
                return Type.getMethodType(Type.DOUBLE_TYPE, Type.INT_TYPE);

            // long(long)
            case Opcodes.LNEG:
                return Type.getMethodType(Type.LONG_TYPE, Type.LONG_TYPE);

            // long()
            case Opcodes.LCONST_0:
            case Opcodes.LCONST_1:
                return Type.getMethodType(Type.LONG_TYPE);

            // long(long, long)
            case Opcodes.LADD:
            case Opcodes.LSUB:
            case Opcodes.LMUL:
            case Opcodes.LDIV:
            case Opcodes.LREM:
            case Opcodes.LAND:
            case Opcodes.LOR:
            case Opcodes.LXOR:
            case Opcodes.LSHL:
            case Opcodes.LSHR:
            case Opcodes.LUSHR:
                return Type.getMethodType(Type.LONG_TYPE, Type.LONG_TYPE, Type.LONG_TYPE);

            // int(long, long)
            case Opcodes.LCMP:
                return Type.getMethodType(Type.INT_TYPE, Type.LONG_TYPE, Type.LONG_TYPE);

            // int(long)
            case Opcodes.L2I:
                return Type.getMethodType(Type.INT_TYPE, Type.LONG_TYPE);

            // float(float)
            case Opcodes.FNEG:
                return Type.getMethodType(Type.FLOAT_TYPE, Type.FLOAT_TYPE);

            // float()
            case Opcodes.FCONST_0:
            case Opcodes.FCONST_1:
            case Opcodes.FCONST_2:
                return Type.getMethodType(Type.FLOAT_TYPE);

            // int(float)
            case Opcodes.F2I:
                return Type.getMethodType(Type.INT_TYPE, Type.FLOAT_TYPE);

            // int(float, float)
            case Opcodes.FCMPL:
            case Opcodes.FCMPG:
                return Type.getMethodType(Type.INT_TYPE, Type.FLOAT_TYPE, Type.FLOAT_TYPE);


            // float(float, float)
            case Opcodes.FADD:
            case Opcodes.FSUB:
            case Opcodes.FMUL:
            case Opcodes.FDIV:
            case Opcodes.FREM:
                return Type.getMethodType(Type.FLOAT_TYPE, Type.FLOAT_TYPE, Type.FLOAT_TYPE);

            // double(double)
            case Opcodes.DNEG:
                return Type.getMethodType(Type.DOUBLE_TYPE, Type.DOUBLE_TYPE);

            // double()
            case Opcodes.DCONST_0:
            case Opcodes.DCONST_1:
                return Type.getMethodType(Type.DOUBLE_TYPE);

            // double(double, double)
            case Opcodes.DADD:
            case Opcodes.DSUB:
            case Opcodes.DMUL:
            case Opcodes.DDIV:
            case Opcodes.DREM:
                return Type.getMethodType(Type.DOUBLE_TYPE, Type.DOUBLE_TYPE, Type.DOUBLE_TYPE);

            // int(double, double)
            case Opcodes.DCMPL:
            case Opcodes.DCMPG:
                return Type.getMethodType(Type.INT_TYPE, Type.DOUBLE_TYPE, Type.DOUBLE_TYPE);

            // float(double)
            case Opcodes.D2F:
                return Type.getMethodType(Type.FLOAT_TYPE, Type.DOUBLE_TYPE);

            // long(double)
            case Opcodes.D2L:
                return Type.getMethodType(Type.LONG_TYPE, Type.DOUBLE_TYPE);
        
            // double(long)
            case Opcodes.L2D:
                return Type.getMethodType(Type.DOUBLE_TYPE, Type.LONG_TYPE);

            // int(double)
            case Opcodes.D2I:
                return Type.getMethodType(Type.INT_TYPE, Type.DOUBLE_TYPE);
            // float(long)
            case Opcodes.L2F:
                return Type.getMethodType(Type.FLOAT_TYPE, Type.LONG_TYPE);
            // long(float)
            case Opcodes.F2L:
                return Type.getMethodType(Type.LONG_TYPE, Type.FLOAT_TYPE);
            // double(float)
            case Opcodes.F2D:
                return Type.getMethodType(Type.DOUBLE_TYPE, Type.FLOAT_TYPE);

            // int(int[], int)
            case Opcodes.IALOAD:
                return Type.getMethodType(Type.INT_TYPE, intArrayType, Type.INT_TYPE);
            // byte(byte[], int)
            case Opcodes.BALOAD:
                return Type.getMethodType(Type.BYTE_TYPE, Type.getType(byte[].class), Type.INT_TYPE);
            // char(char[], int)
            case Opcodes.CALOAD:
                return Type.getMethodType(Type.CHAR_TYPE, Type.getType(char[].class), Type.INT_TYPE);
            // short(short[], int)
            case Opcodes.SALOAD:
                return Type.getMethodType(Type.SHORT_TYPE, Type.getType(short[].class), Type.INT_TYPE);
            // float(float[], int)
            case Opcodes.FALOAD:
                return Type.getMethodType(Type.FLOAT_TYPE, floatArrayType, Type.INT_TYPE);
            // long(long[], int)
            case Opcodes.LALOAD:
                return Type.getMethodType(Type.LONG_TYPE, longArrayType, Type.INT_TYPE);
            // double(double[], int)
            case Opcodes.DALOAD:
                return Type.getMethodType(Type.DOUBLE_TYPE, doubleArrayType, Type.INT_TYPE);
            // T(T[], int)
            case Opcodes.AALOAD: {
                Type arrayType = getType(stack.get(stack.size() - 2));
                return Type.getMethodType(getDirectSubarrayType(arrayType), arrayType, Type.INT_TYPE);
            }
            // void(int[], int, int)
            case Opcodes.IASTORE:
                return Type.getMethodType(Type.VOID_TYPE, intArrayType, Type.INT_TYPE, Type.INT_TYPE);
            // void(byte[], int, byte)
            case Opcodes.BASTORE:
                return Type.getMethodType(Type.VOID_TYPE, Type.getType(byte[].class), Type.INT_TYPE, Type.BYTE_TYPE);
            // void(char[], int, char)
            case Opcodes.CASTORE:
                return Type.getMethodType(Type.VOID_TYPE, Type.getType(char[].class), Type.INT_TYPE, Type.CHAR_TYPE);
            // void(short[], int, short)
            case Opcodes.SASTORE:
                return Type.getMethodType(Type.VOID_TYPE, Type.getType(short[].class), Type.INT_TYPE, Type.SHORT_TYPE);
            // void(float[], int, float)
            case Opcodes.FASTORE:
                return Type.getMethodType(Type.VOID_TYPE, floatArrayType, Type.INT_TYPE, Type.FLOAT_TYPE);
            // void(long[], int, long)
            case Opcodes.LASTORE:
                return Type.getMethodType(Type.VOID_TYPE, longArrayType, Type.INT_TYPE, Type.LONG_TYPE);
            // void(double[], int, double)
            case Opcodes.DASTORE:
                return Type.getMethodType(Type.VOID_TYPE, doubleArrayType, Type.INT_TYPE, Type.DOUBLE_TYPE);
            // void(T[], int, T)
            case Opcodes.AASTORE: {
                Type arrayType = getType(stack.get(stack.size() - 3));
                return Type.getMethodType(Type.VOID_TYPE, arrayType, Type.INT_TYPE, getDirectSubarrayType(arrayType));
            }
            // void(T)
            case Opcodes.POP:
            case Opcodes.MONITORENTER:
            case Opcodes.MONITOREXIT: {
                Type tosType = getType(stack.get(stack.size() - 1));
                return Type.getMethodType(Type.VOID_TYPE, tosType);
            }
            // void(T1, T2)
            case Opcodes.POP2: {
                Type lowType = getType(stack.get(stack.size() - 2));
                if (lowType == Type.LONG_TYPE || lowType == Type.DOUBLE_TYPE) {
                    return Type.getMethodType(Type.VOID_TYPE, lowType);
                }
                Type topType = getType(stack.get(stack.size() - 1));
                return Type.getMethodType(Type.VOID_TYPE, lowType, topType);
            }
            
            // LdcInsnNode.cst.type()
            case Opcodes.LDC: {
                Object cst = ((LdcInsnNode)insn).cst;
                if (cst instanceof Integer) return Type.getMethodType(Type.INT_TYPE);
                if (cst instanceof Float) return Type.getMethodType(Type.FLOAT_TYPE);
                if (cst instanceof Double) return Type.getMethodType(Type.DOUBLE_TYPE);
                if (cst instanceof Long) return Type.getMethodType(Type.LONG_TYPE);
                if (cst instanceof String) return Type.getMethodType(stringType);
                else return null;
            }
            // int(T[])
            case Opcodes.ARRAYLENGTH: {
                Type arrayType = getType(stack.get(stack.size() - 1));
                return Type.getMethodType(Type.INT_TYPE, arrayType);
            }
            
            // boolean(T)
            case Opcodes.INSTANCEOF: {
                Type tosType = getType(stack.get(stack.size() - 1));
                return Type.getMethodType(Type.BOOLEAN_TYPE, tosType);
            }
            
            // FieldInsnNode.desc()
            case Opcodes.GETSTATIC: {
                FieldInsnNode fieldNode = (FieldInsnNode)insn;
                Type fieldType = Type.getType(fieldNode.desc);
                return Type.getMethodType(fieldType);
            }
            // void(FieldInsnNode.desc)
            case Opcodes.PUTSTATIC: {
                FieldInsnNode fieldNode = (FieldInsnNode)insn;
                Type fieldType = Type.getType(fieldNode.desc);
                return Type.getMethodType(Type.VOID_TYPE, fieldType);
            }
            // FieldInsnNode.desc(FieldInsnNode.owner)
            case Opcodes.GETFIELD: {
                FieldInsnNode fieldNode = (FieldInsnNode)insn;
                Type fieldType = Type.getType(fieldNode.desc);
                Type ownerType = Type.getObjectType(fieldNode.owner);
                return Type.getMethodType(fieldType, ownerType);
            }
            // void(FieldInsnNode.owner, FieldInsnNode.desc)
            case Opcodes.PUTFIELD: {
                FieldInsnNode fieldNode = (FieldInsnNode)insn;
                Type fieldType = Type.getType(fieldNode.desc);
                Type ownerType = Type.getObjectType(fieldNode.owner);
                return Type.getMethodType(Type.VOID_TYPE, ownerType, fieldType);
            }

            // T[](int)
            case Opcodes.NEWARRAY:
            case Opcodes.ANEWARRAY: {
                TypeInsnNode typeNode = (TypeInsnNode) insn;
                Type arrayType = Type.getObjectType(typeNode.desc);
                assert(arrayType.getSort() == Type.ARRAY);
                return Type.getMethodType(arrayType, Type.INT_TYPE);
            }
            // T1(T2)
            case Opcodes.CHECKCAST: {
                TypeInsnNode typeNode = (TypeInsnNode) insn;
                Type targetType = Type.getObjectType(typeNode.desc);
                Type sourceType = getType(stack.get(stack.size() - 1));
                return Type.getMethodType(targetType, sourceType);
            }
            // MultiNewArrayInsnNode.desc(count x MultiNewArrayInsnNode.dims)
            case Opcodes.MULTIANEWARRAY: {
                MultiANewArrayInsnNode multiANewArrayNode = (MultiANewArrayInsnNode) insn;
                Type arrayType = Type.getType(multiANewArrayNode.desc);
                Type[] argTypes = Collections.nCopies(multiANewArrayNode.dims, Type.INT_TYPE).toArray(Type[]::new);
                return Type.getMethodType(arrayType, argTypes);
            }
        }
        return null;
    }
}