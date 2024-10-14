package jdk.graal.compiler.test.bytecodefuzz.mutation;

import java.io.Serial;

public class MutationFailedException extends RuntimeException {


    @Serial
    private static final long serialVersionUID = 42L;

    public MutationFailedException(String message) {
        super(message);
    }
}