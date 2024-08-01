package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ArithmeticOp;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64NegateShiftTest2 {
    public static int input = 123;

    public int negShiftRightArithInt()  {
        return -(input >> 7);
    }
}