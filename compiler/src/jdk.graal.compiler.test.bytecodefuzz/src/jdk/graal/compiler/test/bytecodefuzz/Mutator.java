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

import jdk.graal.compiler.test.bytecodefuzz.mutation.*;

public final class Mutator {

    private static List<Mutation> getMutations() {
        return Arrays.asList(
            new InsertJumpMutation(),
            new ConstantMutation(),
            // TODO: work with longs, floats and doubles!
            new InsertNeutralArithmeticMutation(),
            new SplitConstantMutation(),
            new InsertDeadCodeMutation()
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

        if (data == null || data.length == 0) {
            System.err.println("Corrupted Input in Mutation!");
            return null;
        }

        FreeSpace freeSpace = new FreeSpace(maxSize - data.length);
        PseudoRandom prng = new SeededPseudoRandom(seed);
        Mutation mut = prng.pickIn(mutations);
        byte[] result = mut.mutate(data, prng, freeSpace);

        if (result != null) {
            if(result.length > maxSize) {
                System.err.println("Freespace computation does not work out!");
                return null;
            }
            return result;
        }

        System.out.println("First mutation attempt failed!");
        
        NonGrowingMutation nonGrowingMut = prng.pickIn(nonGrowingMutations);
        result = nonGrowingMut.mutate(data, prng);

        if (result == null) {
            System.err.println("None of the mutations worked!");
            return null;
        }
        if(result.length > maxSize) {
            System.err.println("Nongrowing mutation grew over size limit!");
            return null;
        }
        
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
