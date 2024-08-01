package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.common.calc.UnsignedMath;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ControlFlow;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64ConditionalSetTest0 {
    public static int m = 0;

    public static int conditionalSetEQZero()  {
        if ((m & 2) == 0) {
            return 1;
        }
        return 0;
    }
}