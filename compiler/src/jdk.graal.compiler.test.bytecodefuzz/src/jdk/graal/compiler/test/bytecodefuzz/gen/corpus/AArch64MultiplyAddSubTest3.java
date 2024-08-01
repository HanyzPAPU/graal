package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ArithmeticOp;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64MultiplyAddSubTest3 {
    private static long input0 = 43L;
    private static long input1 = 46442L;
    private static long input2 = 2341455L;

    public static long mulAddLong()  {
        return input0 * input1 + input2;
    }
}