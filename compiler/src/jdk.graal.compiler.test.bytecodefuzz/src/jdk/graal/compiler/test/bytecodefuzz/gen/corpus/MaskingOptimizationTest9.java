package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.calc.OrNode;
import jdk.graal.compiler.nodes.calc.ShiftNode;
import org.junit.Test;
public class MaskingOptimizationTest9 {
    public static int x = 42;

    boolean shiftAndMaskCompare1()  {
        return ((x << 2) & 3) == 0; // reduces to return true
    }
}