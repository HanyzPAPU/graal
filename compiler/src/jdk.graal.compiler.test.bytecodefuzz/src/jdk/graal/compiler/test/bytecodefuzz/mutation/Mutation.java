package jdk.graal.compiler.test.bytecodefuzz.mutation;

import jdk.graal.compiler.test.bytecodefuzz.FreeSpace;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import com.code_intelligence.jazzer.mutation.api.PseudoRandom;

public interface Mutation {
    byte[] mutate(byte[] data, PseudoRandom prng);

    default byte[] mutate(byte[] data, PseudoRandom prng, FreeSpace freeSpace) {
        byte[] result;
        try {
            result = mutate(data, prng);
        }
        catch(Throwable e) {
            System.err.println(e.getMessage());
            return null;
        }
        int grownBy = result.length - data.length;
        if (grownBy > freeSpace.amount()) {
            return null;
        }
        else {
            freeSpace.add(-grownBy);
            return result;
        }
    }
}