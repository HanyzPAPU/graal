package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lxor0 {
    private static long a = 1L;
    private static long b = 2L;

    public static long test()  {
        return a ^ b;
    }
}