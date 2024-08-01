package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ArithmeticOp;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64MultiplyAddSubTest5 {
    public static long input0 = Long.MAX_VALUE;
    public static long input1 = 2L;
    public static long input2 = 124454L;

    public static long mulAddLong()  {
        return input0 * input1 + input2;
    }
}