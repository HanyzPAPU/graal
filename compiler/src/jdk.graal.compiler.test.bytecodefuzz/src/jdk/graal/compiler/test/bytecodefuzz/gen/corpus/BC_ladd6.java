package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ladd6 {
    public static long a = -2147483647L;
    public static long b = -2L;

    public static long test()  {
        return a + b;
    }
}