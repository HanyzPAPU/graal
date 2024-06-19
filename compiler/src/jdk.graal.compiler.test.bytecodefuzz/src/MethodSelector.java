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
        double mutCnt = mutCounts.merge(getFullMethodName(method, owner), 1, (old, value) -> old + 1);
        return instructions / mutCnt;
    }

    public static MethodNode select(ClassNode classNode, PseudoRandom prng) {

        String owner = classNode.name;

        // Based on classming method selection
        // [link](doi.org/10.1109/ICSE.2019.00127)

        var methodPotentials = classNode.methods.stream()
            .filter(m -> !m.name.equals(ctorName))
            .map(m -> new MethodWithPotential(m, getPotential(m, owner)))
            .sorted((m1, m2) -> Double.compare(m2.potential(), m1.potential())) // TODO: check if this is descending order
            .toArray(MethodWithPotential[]::new);

        double rand = prng.closedRange(0,1);

        int k = (int)Math.floor(Math.pow(Math.log(1 - rand) / Math.log(epsilon), methodPotentials.length));

        if (k < 0 || k > methodPotentials.length) {
            return null;
        }
        
        return methodPotentials[k].method();
    }
}