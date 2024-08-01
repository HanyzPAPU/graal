package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ArithmeticOp.BinaryConstOp;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64ElideL2ITest3 {
    public static int m = 13;
    public static long n = 40L;

    public int subSingleL2I()  {
        return m - (int) n;
    }
}