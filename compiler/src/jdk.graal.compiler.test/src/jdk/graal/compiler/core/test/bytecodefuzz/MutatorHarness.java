package jdk.graal.compiler.core.test.bytecodefuzz;

public class MutatorHarness {
    
    static {
        System.loadLibrary("mutator");
    }

    static Mutator mutator;

    // TODO: reimplement?

    private static byte[] readFromMem(long data, int size){
        byte[] result = new byte[size];
        UNSAFE.copyMemory(null, data, result, BYTE_ARRAY_OFFSET, size);
        return result;
    }

    private static int writeToMem(long target, byte[] data, int maxSize){
        int size = Math.min(data.length, maxSize);
        UNSAFE.copyMemory(data, BYTE_ARRAY_OFFSET, null, target, size);
        return size;
    }

    private static int mutate(long data, int size, int maxSize, int seed) {
        
        // TODO: libFuzzer sends '\n' as a special signal when there are no corpus entries -> do we want to consider this case?

        byte[] inputData = readFromMem(data, size);
        byte[] outputData = mutator.Mutate(inputData, maxSize, seed);
        //TODO: Assert(outputData.length <= maxSize)

        return writeToMem(data, outputData, maxSize);
    }

    private static native void initMutator();

    public static void InitMutator() {
        mutator = new Mutator();
        initMutator();
    }
}
