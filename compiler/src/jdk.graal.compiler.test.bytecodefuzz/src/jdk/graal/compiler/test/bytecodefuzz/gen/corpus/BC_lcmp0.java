package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lcmp0 {
    public static long a = 0L;
    public static long b = -1L;

    public static boolean test()  {
        return a < b;
    }
}