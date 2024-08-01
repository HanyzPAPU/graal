package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Math_exact4 {
    private static long a = 1L;
    private static long b = Long.MAX_VALUE;

    public static long testLongAddExact()  {
        return Math.addExact(a, b);
    }
}