package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Catch_NPE_101 {
    private static int a = 1;

    public static int test()  {
        int r = 0;
        try {
            r = 0;
            if (a == 0) {
                throw null;
            }
            r = 1;
            if (a - 1 == 0) {
                throw null;
            }
        } catch (NullPointerException e) {
            return r + 10;
        }
        return r;
    }
}