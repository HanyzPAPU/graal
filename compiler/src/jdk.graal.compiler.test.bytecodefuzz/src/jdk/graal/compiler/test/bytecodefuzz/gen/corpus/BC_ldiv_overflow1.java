package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ldiv_overflow1 {
    public static long a = Long.MIN_VALUE;
    public static long b = 1L;

    public static long test()  {
        return a / (b | 1);
    }
}