package jdk.graal.compiler.core.test.bytecodefuzz;

public class MutatorHarness {
    
    static {
        System.loadLibrary("mutator");
    }

    static Mutator mutator;

    private static byte[] mutate(byte[] data, int maxSize, int seed) {
        
        // TODO: libFuzzer sends '\n' as a special signal when there are no corpus entries -> do we want to consider this case?

        var mutatedData = mutator.Mutate(data, maxSize, seed);

        //TODO: Assert(mutatedData.length <= maxSize)

        return mutatedData;
    }

    private static native void initMutator();

    public static void InitMutator() {
        mutator = new Mutator();
        initMutator();
    }
}
