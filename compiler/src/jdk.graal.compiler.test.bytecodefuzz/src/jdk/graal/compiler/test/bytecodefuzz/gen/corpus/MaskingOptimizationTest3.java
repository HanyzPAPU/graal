package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.calc.OrNode;
import jdk.graal.compiler.nodes.calc.ShiftNode;
import org.junit.Test;
public class MaskingOptimizationTest3 {
    public static long x = 42L;
    public static long y = 35L;

    public long shiftAndMask4()  {
        return (y + (x << 2)) & 3; // reduces to return y & 3
    }
}