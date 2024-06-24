package jdk.graal.compiler.test.bytecodefuzz;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import com.code_intelligence.jazzer.mutation.api.PseudoRandom;

public interface NonGrowingMutation extends Mutation {
    default void mutate(ClassReader reader, ClassWriter writer, PseudoRandom prng) {
        mutate(reader, writer, new FreeSpace(0), prng);
    }
}