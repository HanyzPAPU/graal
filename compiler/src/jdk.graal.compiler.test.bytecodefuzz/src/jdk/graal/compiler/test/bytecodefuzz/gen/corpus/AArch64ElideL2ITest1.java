package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ArithmeticOp.BinaryConstOp;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64ElideL2ITest1 {
    public static long m = 5L;
    public static long n = 0x1FFFFFFFFL;

    public int addWithTwoL2I()  {
        return (int) m + (int) n;
    }
}