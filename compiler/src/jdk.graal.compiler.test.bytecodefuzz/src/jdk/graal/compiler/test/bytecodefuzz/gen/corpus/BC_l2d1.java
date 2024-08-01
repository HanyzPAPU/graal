package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_l2d1 {
    private static long a = 1L;

    public static double test()  {
        return a;
    }
}