package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Math_exact10 {
    private static long a = -2L;
    private static long b = Long.MAX_VALUE;

    public static long testLongSubExact()  {
        return Math.subtractExact(a, b);
    }
}