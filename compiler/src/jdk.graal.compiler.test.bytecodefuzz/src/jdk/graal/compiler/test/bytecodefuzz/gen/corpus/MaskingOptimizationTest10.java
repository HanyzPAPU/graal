package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.calc.OrNode;
import jdk.graal.compiler.nodes.calc.ShiftNode;
import org.junit.Test;
public class MaskingOptimizationTest10 {
    public static long x = 42L;

    public boolean shiftAndMaskCompare2()  {
        return ((x << 2) & 3) == 0; // reduces to return true
    }
}