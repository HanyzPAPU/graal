package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_l2f4 {
    private static long a = Long.MIN_VALUE;

    public static float test()  {
        return a;
    }
}