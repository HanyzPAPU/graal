package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ControlFlow;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64CbzTest5 {
    private static String s = "abc";

    public static String isNull()  {
        if (s == null) {
            return "abc";
        } else {
            return s + "abc";
        }
    }
}