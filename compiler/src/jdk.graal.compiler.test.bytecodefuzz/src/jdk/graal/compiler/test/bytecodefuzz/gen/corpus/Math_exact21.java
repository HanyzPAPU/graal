package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Math_exact21 {
    private static long a = 0L;

    public static long testLongNegExact()  {
        return Math.negateExact(a);
    }
}