package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Math_exact15 {
    public static long a = 1L;
    public static long b = 2L;

    public static long testLongMulExact()  {
        return Math.multiplyExact(a, b);
    }
}