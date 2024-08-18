package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.calc.OrNode;
import jdk.graal.compiler.nodes.calc.ShiftNode;
import org.junit.Test;
public class MaskingOptimizationTest7 {
    public static int x = 42;
    public static int y = 35;

    public long shiftAndMask8()  {
        return (y + (x << 2)) & 3; // reduces to y & 3
    }
}