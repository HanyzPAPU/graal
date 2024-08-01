package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IfCanonicalizerSwapTest2 {
    private static long value = 10L;

    public static String testSnippet1()  {
        if (value >= 0L && value <= 35L) {
            return "JustRight";
        } else {
            if (value > 35L) {
                return "TooHot";
            } else {
                return "TooCold";
            }
        }
    }
}