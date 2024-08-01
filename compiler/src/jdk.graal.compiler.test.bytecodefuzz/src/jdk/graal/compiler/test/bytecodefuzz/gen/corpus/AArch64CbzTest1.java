package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ControlFlow;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64CbzTest1 {
    private static int x = 1;

    public static int equalsTo()  {
        if (x == 0) {
            return 1;
        } else {
            return x - 1;
        }
    }
}