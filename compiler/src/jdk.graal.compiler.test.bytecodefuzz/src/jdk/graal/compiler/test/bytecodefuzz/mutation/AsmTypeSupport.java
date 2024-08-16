package jdk.graal.compiler.test.bytecodefuzz.mutation;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Map;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import jdk.graal.compiler.test.bytecodefuzz.FieldHolder;

public final class AsmTypeSupport {

    public static final Type objectType = Type.getType(Object.class);
    
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

}