package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.calc.OrNode;
import jdk.graal.compiler.nodes.calc.ShiftNode;
import org.junit.Test;
public class MaskingOptimizationTest20 {
    private static long r = 0L;

    long addAndMaskCarry()  {
        long r2 = r;
        for (int x = 3; x > 1; x--) {
            for (int y = 1; 2 > y; y++) {
                r2 += y;
                r2 &= x;
            }
        }
        return r2;
    }
}