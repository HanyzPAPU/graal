package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ArithmeticOp;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64MultiplyAddSubTest11 {
    public static long input0 = Long.MIN_VALUE;
    public static long input1 = 2L;
    public static long input2 = Long.MAX_VALUE;

    public static long mulSubLong()  {
        return input2 - input0 * input1;
    }
}