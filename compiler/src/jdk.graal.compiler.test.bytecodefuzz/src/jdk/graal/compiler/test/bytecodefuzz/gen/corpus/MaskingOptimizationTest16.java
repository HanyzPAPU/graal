package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.calc.OrNode;
import jdk.graal.compiler.nodes.calc.ShiftNode;
import org.junit.Test;
public class MaskingOptimizationTest16 {
    public static int x = 42;
    public static int y = 35;

    public boolean shiftAndMaskCompare8()  {
        return ((y + (x << 2)) & 3) == 0; // reduces to y & 3 == 0
    }
}