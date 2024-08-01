package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.lir.LIRInstruction;
import jdk.graal.compiler.lir.aarch64.AArch64ControlFlow;
import org.junit.Test;
import java.util.function.Predicate;
public class AArch64CbzTest7 {
    public static String s = "abc";

    public static String isNotNull()  {
        if (s != null) {
            return s + "abc";
        } else {
            return "abc";
        }
    }
}