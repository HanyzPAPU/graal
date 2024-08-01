package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_l2i6 {
    public static long a = 2147483647L;

    public static int test()  {
        return (int) a;
    }
}