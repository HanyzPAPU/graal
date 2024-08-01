package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.common.calc.UnsignedMath;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ControlFlow;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64ConditionalSetTest5 {
    public static int m = 3;
    public static int n = 2;

    public static int conditionalSetLT()  {
        if (m < n) {
            return 1;
        }
        return 0;
    }
}