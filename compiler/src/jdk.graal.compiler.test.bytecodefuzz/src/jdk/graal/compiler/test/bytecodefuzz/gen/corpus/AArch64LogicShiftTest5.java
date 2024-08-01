package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ArithmeticOp;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64LogicShiftTest5 {
    public static long input0 = 1234567L;
    public static long input1 = 123L;

    public static long eorShiftLong()  {
        long value = input0 ^ (input1 << 5);
        value += input0 ^ (input1 >> 5);
        value += input0 ^ (input1 >>> 5);
        return value;
    }
}