package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lxor3 {
    private static long a = 6L;
    private static long b = 4L;

    public static long test()  {
        return a ^ b;
    }
}