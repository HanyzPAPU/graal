package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lshl1 {
    private static long a = 0L;
    private static int b = -1;

    public static long test()  {
        return a << b;
    }
}