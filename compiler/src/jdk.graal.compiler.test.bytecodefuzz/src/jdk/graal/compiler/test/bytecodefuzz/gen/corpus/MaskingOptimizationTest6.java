package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.calc.OrNode;
import jdk.graal.compiler.nodes.calc.ShiftNode;
import org.junit.Test;
public class MaskingOptimizationTest6 {
    public static int x = 42;

    public long shiftAndMask7()  {
        return ((long) (x << 2)) & 3; // reduces to return 0
    }
}