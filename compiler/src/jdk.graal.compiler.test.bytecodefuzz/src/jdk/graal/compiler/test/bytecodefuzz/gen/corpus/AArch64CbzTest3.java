package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ControlFlow;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64CbzTest3 {
    public static int x = 1;

    public static int notEqualsTo()  {
        if (x != 0) {
            return x + 2;
        } else {
            return 3;
        }
    }
}