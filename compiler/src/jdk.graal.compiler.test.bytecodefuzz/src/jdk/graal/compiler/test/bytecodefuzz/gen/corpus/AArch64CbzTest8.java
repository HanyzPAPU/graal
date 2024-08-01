package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ControlFlow;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64CbzTest8 {
    private static String s1 = "ab";
    private static String s2 = "ac";

    public static String objectEquals()  {
        if (s1.equals(s2)) {
            return s1 + "abc";
        } else {
            return s2 + "abd";
        }
    }
}