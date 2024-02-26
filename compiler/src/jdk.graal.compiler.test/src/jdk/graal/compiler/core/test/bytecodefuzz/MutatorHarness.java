package jdk.graal.compiler.core.test.bytecodefuzz;

public class MutatorHarness {
    
    static {
        System.out.println("Mutator Harness Static CTOR");
        System.loadLibrary("mutator");
    }

    public static int Mutate(long data, int size, int maxSize, int seed) {
        System.out.println("Hello from custom mutate!");
        return size;
    }

    public static native void initMutator();
}
