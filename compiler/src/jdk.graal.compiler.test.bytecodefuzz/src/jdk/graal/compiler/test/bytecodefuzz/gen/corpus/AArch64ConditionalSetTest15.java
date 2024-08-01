package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.common.calc.UnsignedMath;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ControlFlow;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64ConditionalSetTest15 {
    public static Object obj = Integer.valueOf(1);

    public static int conditionalSetIsNull()  {
        if (obj == null) {
            return 1;
        }
        return 0;
    }
}