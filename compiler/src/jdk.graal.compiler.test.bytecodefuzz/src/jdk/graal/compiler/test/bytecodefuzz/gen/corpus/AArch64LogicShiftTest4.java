package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ArithmeticOp;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64LogicShiftTest4 {
    public static int input0 = 123;
    public static int input1 = 425;

    public static int eorShiftInt()  {
        int value = input0 ^ (input1 << 5);
        value += input0 ^ (input1 >> 5);
        value += input0 ^ (input1 >>> 5);
        return value;
    }
}