package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.calc.OrNode;
import jdk.graal.compiler.nodes.calc.ShiftNode;
import org.junit.Test;
public class MaskingOptimizationTest14 {
    private static long x = 42L;
    private static long y = 35L;

    boolean shiftAndMaskCompare6()  {
        return (((y << 2) + (x << 2)) & 3) == 0; // reduces to return true
    }
}