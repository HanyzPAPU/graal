package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ArithmeticOp;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64MultiplyAddSubTest4 {
    private static long input0 = -3141L;
    private static long input1 = -542324L;
    private static long input2 = 65225L;

    public static long mulAddLong()  {
        return input0 * input1 + input2;
    }
}