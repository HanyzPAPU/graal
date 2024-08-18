package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.calc.OrNode;
import jdk.graal.compiler.nodes.calc.ShiftNode;
import org.junit.Test;
public class MaskingOptimizationTest19 {
    public static long x = 42L;

    public long orAndMask2()  {
        return (x | 4L) & 3L; // reduces to return x & 3
    }
}