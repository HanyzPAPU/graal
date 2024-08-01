package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.calc.OrNode;
import jdk.graal.compiler.nodes.calc.ShiftNode;
import org.junit.Test;
public class MaskingOptimizationTest13 {
    private static int x = 42;
    private static int y = 35;

    boolean shiftAndMaskCompare5()  {
        return (((y << 2) + (x << 2)) & 3) == 0; // reduces to return true
    }
}