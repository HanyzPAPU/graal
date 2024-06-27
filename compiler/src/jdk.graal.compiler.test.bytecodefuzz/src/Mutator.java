package jdk.graal.compiler.test.bytecodefuzz;

import java.lang.Exception;
import java.util.Arrays;
import java.util.List;
import java.io.StringWriter;
import java.io.PrintWriter;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceMethodVisitor;
import org.objectweb.asm.util.TraceClassVisitor;

import com.code_intelligence.jazzer.mutation.api.PseudoRandom;
import com.code_intelligence.jazzer.mutation.engine.SeededPseudoRandom;


public final class Mutator {

    private static List<Mutation> getMutations() {
        return Arrays.asList(
            new InsertJumpMutation(),
            new ConstantMutation()
        );
    }

    private static List<NonGrowingMutation> getNonGrowingMutations() {
        return Arrays.asList(
            new ConstantMutation()
        );
    }

    private final List<Mutation> mutations = getMutations();
    private final List<NonGrowingMutation> nonGrowingMutations = getNonGrowingMutations();

    public byte[] Mutate(byte[] data, int maxSize, int seed) throws Exception {

        // dumpBytes(data);
        // dumpBytecode(data);

        // System.out.println(data.length);

        ClassReader reader = new ClassReader(data);
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        FreeSpace freeSpace = new FreeSpace(maxSize - data.length);
        PseudoRandom prng = new SeededPseudoRandom(seed);
        byte[] result;

        Mutation mut = prng.pickIn(mutations);

        mut.mutate(reader, writer, freeSpace, prng);

        if (freeSpace.amount() >= 0) {
            result = writer.toByteArray();
            assert(result.length <= maxSize);
            return result;
        }

        NonGrowingMutation nonGrowingMut = prng.pickIn(nonGrowingMutations);
        writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        nonGrowingMut.mutate(reader, writer, prng);

        result = writer.toByteArray();
        assert(result.length <= maxSize);
    
        return result;
    }

    public void Init(int seed){
        return;
    }

    private void dumpBytecode(byte[] bytecode) {
        ClassReader reader = new ClassReader(bytecode);
        StringWriter sw = new StringWriter();
        TraceClassVisitor visitor = new TraceClassVisitor(new PrintWriter(sw));
        reader.accept(visitor, 0);
        System.err.println(sw.toString());
    }

    private void dumpBytes(byte[] bytes) {
        for (int i = 0; i < bytes.length; ++i) {
            System.err.printf("%02x", bytes[i]);

            if ((i+1) % 16 == 0) {
                System.err.println();
            }
            else if ((i+1) % 4 == 0) {
                System.err.print(" ");
            }
        }
        System.err.println();
    }
}
