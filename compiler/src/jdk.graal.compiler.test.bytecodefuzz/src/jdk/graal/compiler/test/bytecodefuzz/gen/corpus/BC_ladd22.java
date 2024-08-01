package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ladd22 {
    private static int a = 33;
    private static int b = 67;

    public static long test()  {
        return a + (long) b;
    }
}