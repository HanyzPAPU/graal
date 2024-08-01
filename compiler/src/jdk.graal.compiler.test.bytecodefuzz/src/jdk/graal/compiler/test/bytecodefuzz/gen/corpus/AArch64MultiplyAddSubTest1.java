package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ArithmeticOp;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64MultiplyAddSubTest1 {
    public static int input0 = -3;
    public static int input1 = -5;
    public static int input2 = 6;

    public static int mulAddInt()  {
        return input2 + input0 * input1;
    }
}