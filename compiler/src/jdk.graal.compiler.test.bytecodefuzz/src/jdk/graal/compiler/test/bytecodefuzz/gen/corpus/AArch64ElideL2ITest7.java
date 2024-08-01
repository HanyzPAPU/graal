package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ArithmeticOp.BinaryConstOp;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64ElideL2ITest7 {
    public static long m = 234L;
    public static long n = 3L;

    public int logicWithTwoL2I()  {
        return (int) m | (int) n;
    }
}