package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.common.calc.UnsignedMath;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ControlFlow;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64ConditionalSetTest6 {
    public static int m = 1;
    public static int n = 2;

    public static boolean conditionalSetBT()  {
        return UnsignedMath.belowThan(m, n);
    }
}