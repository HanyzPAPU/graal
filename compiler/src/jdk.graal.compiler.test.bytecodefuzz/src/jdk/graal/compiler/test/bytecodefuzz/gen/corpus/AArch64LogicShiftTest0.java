package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ArithmeticOp;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64LogicShiftTest0 {
    private static int input0 = 123;
    private static int input1 = 425;

    public static int andShiftInt()  {
        int value = input0 & (input1 << 5);
        value += input0 & (input1 >> 5);
        value += input0 & (input1 >>> 5);
        return value;
    }
}