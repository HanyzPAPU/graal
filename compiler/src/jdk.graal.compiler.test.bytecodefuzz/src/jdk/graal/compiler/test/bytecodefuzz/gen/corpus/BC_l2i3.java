package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_l2i3 {
    private static long a = -1L;

    public static int test()  {
        return (int) a;
    }
}