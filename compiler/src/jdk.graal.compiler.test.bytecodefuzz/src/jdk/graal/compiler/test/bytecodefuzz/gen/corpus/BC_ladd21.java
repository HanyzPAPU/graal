package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ladd21 {
    private static int a = 0;
    private static int b = -1;

    public static long test()  {
        return a + (long) b;
    }
}