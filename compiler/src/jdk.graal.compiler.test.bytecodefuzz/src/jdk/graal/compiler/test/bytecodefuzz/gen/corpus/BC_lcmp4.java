package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lcmp4 {
    public static long a = -1L;
    public static long b = Long.MIN_VALUE;

    public static boolean test()  {
        return a < b;
    }
}