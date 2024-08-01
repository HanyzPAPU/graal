package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Math_exact5 {
    public static long a = -1L;
    public static long b = Long.MIN_VALUE;

    public static long testLongAddExact()  {
        return Math.addExact(a, b);
    }
}