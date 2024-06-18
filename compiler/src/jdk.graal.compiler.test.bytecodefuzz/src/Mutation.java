package jdk.graal.compiler.test.bytecodefuzz;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import com.code_intelligence.jazzer.mutation.api.PseudoRandom;

public interface Mutation {
    void mutate(ClassReader reader, ClassWriter writer, FreeSpace freeSpace, PseudoRandom prng);
}