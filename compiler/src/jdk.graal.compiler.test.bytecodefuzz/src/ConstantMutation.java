package jdk.graal.compiler.test.bytecodefuzz;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import com.code_intelligence.jazzer.mutation.api.PseudoRandom;

public class ConstantMutation implements Mutation {
    public void mutate(ClassReader reader, ClassWriter writer, FreeSpace freeSpace, PseudoRandom prng) {
        MutateConstantClassVisitor mutateConstants = new MutateConstantClassVisitor(Opcodes.ASM9, writer, prng, true, freeSpace);
        reader.accept(mutateConstants, 0);
    }
}