package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ifnonnull1 {
    private static Object a = "";

    public static int test()  {
        int n = 0;
        if (a == null) {
            n += 1;
        } else {
            n -= 1;
        }
        if (a != null) {
            n -= 1;
        } else {
            n += 1;
        }
        return n;
    }
}