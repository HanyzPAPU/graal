package jdk.graal.compiler.test.bytecodefuzz.mutation;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public final class AsmTypeSupport {
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