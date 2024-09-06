package jdk.graal.compiler.test.bytecodefuzz.mutation;

public class MutationFailedException extends RuntimeException {

    static final long serialVersionUID = 42L;

    public MutationFailedException(String message) {
        super(message);
    }
}