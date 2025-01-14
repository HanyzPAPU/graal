package jdk.graal.compiler.test.bytecodefuzz;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.io.StringWriter;
import java.io.PrintWriter;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.TraceClassVisitor;

import com.code_intelligence.jazzer.mutation.api.PseudoRandom;
import com.code_intelligence.jazzer.mutation.engine.SeededPseudoRandom;

import jdk.graal.compiler.test.bytecodefuzz.mutation.*;

public final class Mutator {

    private static final boolean conservative;
    private static final boolean hasJumpInsertion;
    private static final boolean onlyJumps;

    private static boolean getBoolProperty(String name, boolean defaultVal) {
        String property = System.getProperty(name);
        if (property == null) {
            return defaultVal;
        }
        if (property.equals("true")) {
            return true;
        }
        if (property.equals("false")) {
            return false;
        }
        System.err.println("Invalid format for " + name + " property: " + property);
        return defaultVal;
    }

    static {
        conservative = getBoolProperty("fuzzMutConservative", false);
        hasJumpInsertion = getBoolProperty("fuzzMutHasJump", true);
        onlyJumps = getBoolProperty("fuzzMutJumpOnly", false);
    }

    private static List<Mutation> getMutations() {

        if (onlyJumps) {
            return Arrays.asList(new InsertJumpMutation());
        }

        // Start with conservative mutations
        List<Mutation> mutations = new ArrayList<>(Arrays.asList(
            new InsertNeutralArithmeticMutation(),
            new SplitConstantMutation(),
            new InsertDeadCodeMutation(),
            new InsertLocalVariableMutation(),
            new InsertEscapeMutation(),
            new InsertMethodCallMutation()
        ));

        // Add jump insertion if desired
        if (hasJumpInsertion) {
            mutations.add(new InsertJumpMutation());
        }

        // Conservative shortcircuit
        if (conservative) {
            // Allow ctor constant fuzzing
            mutations.add(new ConstantMutation());
            return mutations;
        }

        // Add aggresive mutations
        mutations.addAll(Arrays.asList(
            new ConstantMutation(false),
            new InsertSwapMutation(),
            new InsertWriteMutation(),
            new InsertOperationMutation()
        ));

        return mutations;
    }

    private static List<NonGrowingMutation> getNonGrowingMutations() {
        return Arrays.asList(
            new ConstantMutation(),
            new RemoveEscapeMutation()
        );
    }

    private final List<Mutation> mutations = getMutations();
    private final List<NonGrowingMutation> nonGrowingMutations = getNonGrowingMutations();

    public byte[] Mutate(byte[] data, int maxSize, int seed) {

        if (data == null || data.length < 10) {
            // The required classfile header has 10 B, so don't even bother opening anything smaller
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

        NonGrowingMutation nonGrowingMut = prng.pickIn(nonGrowingMutations);
        result = nonGrowingMut.mutate(data, prng);

        if (result == null) {
            System.err.println("None of the mutations worked!");
            return null;
        }
        if(result.length > maxSize) {
            System.err.println("Non-growing mutation grew over size limit!");
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
        System.err.println(sw);
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
