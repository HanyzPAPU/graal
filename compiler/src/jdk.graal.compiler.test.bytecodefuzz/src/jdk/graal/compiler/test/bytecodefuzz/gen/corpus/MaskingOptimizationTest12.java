package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.calc.OrNode;
import jdk.graal.compiler.nodes.calc.ShiftNode;
import org.junit.Test;
public class MaskingOptimizationTest12 {
    public static long x = 42L;
    public static long y = 35L;

    boolean shiftAndMaskCompare4()  {
        return ((y + (x << 2)) & 3) == 0; // reduces to return y & 3 == 0
    }
}