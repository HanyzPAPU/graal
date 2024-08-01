package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ldiv_overflow1 {
    private static long a = Long.MIN_VALUE;
    private static long b = 1L;

    public static long test()  {
        return a / (b | 1);
    }
}