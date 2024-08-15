package jdk.graal.compiler.test.bytecodefuzz.mutation;

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
}