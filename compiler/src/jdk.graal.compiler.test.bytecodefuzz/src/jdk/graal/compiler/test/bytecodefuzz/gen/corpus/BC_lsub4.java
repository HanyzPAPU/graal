package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lsub4 {
    private static long a = -2147483648L;
    private static long b = -1L;

    public static long test()  {
        return a - b;
    }
}