package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ladd26 {
    private static int a = -2147483647;
    private static int b = -2;

    public static long test()  {
        return a + (long) b;
    }
}