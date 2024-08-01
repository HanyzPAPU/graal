package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Math_exact9 {
    private static long a = 1L;
    private static long b = 2L;

    public static long testLongSubExact()  {
        return Math.subtractExact(a, b);
    }
}