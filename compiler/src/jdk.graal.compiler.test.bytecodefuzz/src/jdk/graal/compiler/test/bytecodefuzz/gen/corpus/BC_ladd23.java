package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ladd23 {
    public static int a = 1;
    public static int b = -1;

    public static long test()  {
        return a + (long) b;
    }
}