package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class BC_lshr_C320 {
    private static long a = 87224824140L;

    public static long test()  {
        return a >> 32;
    }
}