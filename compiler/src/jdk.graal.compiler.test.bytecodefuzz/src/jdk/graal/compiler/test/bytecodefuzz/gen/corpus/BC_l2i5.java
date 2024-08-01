package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_l2i5 {
    public static long a = -2147483648L;

    public static int test()  {
        return (int) a;
    }
}