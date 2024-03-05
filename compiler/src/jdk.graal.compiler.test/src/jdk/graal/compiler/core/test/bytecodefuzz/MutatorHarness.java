package jdk.graal.compiler.core.test.bytecodefuzz;

public class MutatorHarness {
    
    static {
        System.loadLibrary("mutator");
    }

    static Mutator mutator;

    private static byte[] mutate(byte[] data, int maxSize, int seed) throws Exception {
        
        // TODO: libFuzzer sends '\n' as a special signal when there are no corpus entries -> do we want to consider this case?

        assert(data != null);
        
        var res = mutator.Mutate(data, maxSize, seed);

        assert(res != null);
        assert(res.length <= maxSize);

        return res;
    }

    private static native void initMutator();

    public static void InitMutator() {
        mutator = new Mutator();
        initMutator();
    }
}
