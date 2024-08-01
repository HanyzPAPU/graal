package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lcmp2 {
    private static long a = -1L;
    private static long b = 0L;

    public static boolean test()  {
        return a < b;
    }
}