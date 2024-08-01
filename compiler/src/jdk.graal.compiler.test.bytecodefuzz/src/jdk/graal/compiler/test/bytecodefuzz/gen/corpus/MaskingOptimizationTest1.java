package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.calc.OrNode;
import jdk.graal.compiler.nodes.calc.ShiftNode;
import org.junit.Test;
public class MaskingOptimizationTest1 {
    private static long x = 42L;

    long shiftAndMask2()  {
        return (x << 2) & 3; // reduces to return 0
    }
}