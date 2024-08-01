package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.common.calc.UnsignedMath;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ControlFlow;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64ConditionalSetTest13 {
    public static Integer m = Integer.valueOf(2);
    public static Integer n = Integer.valueOf(2);

    public static int conditionalSetObjectEQ()  {
        if (m == n) {
            return 1;
        }
        return 0;
    }
}