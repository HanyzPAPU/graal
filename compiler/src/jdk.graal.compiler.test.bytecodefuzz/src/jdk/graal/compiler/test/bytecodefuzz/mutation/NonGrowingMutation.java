package jdk.graal.compiler.test.bytecodefuzz.mutation;

import jdk.graal.compiler.test.bytecodefuzz.FreeSpace;
import com.code_intelligence.jazzer.mutation.api.PseudoRandom;

public interface NonGrowingMutation extends Mutation {
    default byte[] mutate(byte[] data, PseudoRandom prng) {
        return mutate(data, prng, new FreeSpace(0));
    }

    byte[] mutate(byte[] data, PseudoRandom prng, FreeSpace freeSpace);
}
