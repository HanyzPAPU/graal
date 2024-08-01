package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.common.calc.UnsignedMath;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ControlFlow;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64ConditionalSetTest3 {
    private static int m = 2;
    private static int n = 2;

    public static int conditionalSetEQ()  {
        if (m == n) {
            return 1;
        }
        return 0;
    }
}