package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lmul7 {
    private static long a = 1000000L;
    private static long b = 1000000L;

    public static long test()  {
        return a * b;
    }
}