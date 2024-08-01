package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ArithmeticOp;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64NegateShiftTest3 {
    private static long input = 123L;

    public long negShiftLeftLogicLong()  {
        return -(input << 8);
    }
}