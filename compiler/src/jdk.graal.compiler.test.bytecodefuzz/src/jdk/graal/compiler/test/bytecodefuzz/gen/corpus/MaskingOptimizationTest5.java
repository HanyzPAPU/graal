package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.calc.OrNode;
import jdk.graal.compiler.nodes.calc.ShiftNode;
import org.junit.Test;
public class MaskingOptimizationTest5 {
    private static long x = 42L;
    private static long y = 35L;

    long shiftAndMask6()  {
        return ((y << 2) + (x << 2)) & 3; // reduces to return 0
    }
}