package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ArithmeticOp.BinaryConstOp;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64ElideL2ITest2 {
    private static long m = 0x80000000L;
    private static long n = 6L;

    public int addWithTwoNarrow()  {
        return (int) m + (short) n;
    }
}