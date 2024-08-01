package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ArithmeticOp.BinaryConstOp;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64ElideL2ITest6 {
    private static long a = 0xFFFFFFFFL;
    private static int m = 123;

    public long shiftLongWithL2I()  {
        return a + ((m & 0xFFFFFFFFL) << (int) a);
    }
}