package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.calc.OrNode;
import jdk.graal.compiler.nodes.calc.ShiftNode;
import org.junit.Test;
public class MaskingOptimizationTest2 {
    public static int x = 42;
    public static int y = 35;

    int shiftAndMask3()  {
        return (y + (x << 2)) & 3; // reduces to return y & 3
    }
}