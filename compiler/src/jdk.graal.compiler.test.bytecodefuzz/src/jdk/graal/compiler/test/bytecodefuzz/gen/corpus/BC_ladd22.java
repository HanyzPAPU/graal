package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ladd22 {
    public static int a = 33;
    public static int b = 67;

    public static long test()  {
        return a + (long) b;
    }
}