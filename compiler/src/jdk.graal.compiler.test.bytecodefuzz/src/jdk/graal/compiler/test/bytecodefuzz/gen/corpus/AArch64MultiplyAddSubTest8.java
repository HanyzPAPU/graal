package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ArithmeticOp;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64MultiplyAddSubTest8 {
    public static int input0 = Integer.MIN_VALUE;
    public static int input1 = 2;
    public static int input2 = Integer.MAX_VALUE;

    public static int mulSubInt()  {
        return input2 - input0 * input1;
    }
}