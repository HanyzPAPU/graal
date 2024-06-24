package jdk.graal.compiler.test.bytecodefuzz;

import java.lang.Math;
import java.util.HashMap;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import com.code_intelligence.jazzer.mutation.api.PseudoRandom;

public class MethodSelector {
    private MethodSelector() {};

    private static final String ctorName = "<init>";
    private static final double epsilon = 0.05;

    private static HashMap<String, Integer> mutCounts = new HashMap<>();

    private static record MethodWithPotential(MethodNode method, double potential){}

    private static String getFullMethodName(MethodNode method, String owner) {
        return owner + "." + method.name + method.desc;
    }

    private static double getPotential(MethodNode method, String owner) {
        double instructions = method.instructions.size();
        double mutCount = mutCounts.getOrDefault(getFullMethodName(method, owner), 1);
        return instructions / mutCount;
    }

    private static int pickRandomMethodIndex(PseudoRandom prng, int methodCount) {
        double rand;
        int k;

        do {
            // Pick from epsilon to avoid generating indices out of bounds.
            // We still check just to be sure, because numerical stability could bite us
            rand = prng.closedRange(epsilon, 1.0);
            k = (int)Math.floor(methodCount * Math.log(rand) / Math.log(epsilon));
        } while (k >= methodCount);

        return k;
    }

    public static MethodNode select(ClassNode classNode, PseudoRandom prng, boolean includeCtor) {

        String owner = classNode.name;

        // Based on classming method selection
        // [link](doi.org/10.1109/ICSE.2019.00127)

        MethodNode[] methodsSortedByPotential = classNode.methods.stream()
            .filter(m -> includeCtor || !m.name.equals(ctorName))
            .map(m -> new MethodWithPotential(m, getPotential(m, owner)))
            .sorted((m1, m2) -> Double.compare(m2.potential(), m1.potential())) // TODO: check if this is descending order
            .map(m -> m.method())
            .toArray(MethodNode[]::new);

        int k = pickRandomMethodIndex(prng, methodsSortedByPotential.length);

        MethodNode result = methodsSortedByPotential[k];

        // Optimistically expect that the mutation will succeed and add 1 to mutCount
        mutCounts.merge(getFullMethodName(result, owner), 1, (old, val) -> old + 1);
        
        return result;
    }

    public static MethodNode select(ClassNode classNode, PseudoRandom prng) {
        return select(classNode, prng, false);
    }
}