package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.common.calc.UnsignedMath;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ControlFlow;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64ConditionalSetTest8 {
    private static float m = 1.0f;
    private static float n = 2.0f;

    public static int conditionalSetFPEQ()  {
        if (m == n) {
            return 1;
        }
        return 0;
    }
}