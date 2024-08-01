package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Math_exact17 {
    private static long a = -2L;
    private static long b = Long.MIN_VALUE;

    public static long testLongMulExact()  {
        return Math.multiplyExact(a, b);
    }
}