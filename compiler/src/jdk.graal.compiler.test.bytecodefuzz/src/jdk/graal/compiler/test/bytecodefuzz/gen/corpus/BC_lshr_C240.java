package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lshr_C240 {
    private static long a = 87224824140L;

    public static long test()  {
        return a >> 24;
    }
}