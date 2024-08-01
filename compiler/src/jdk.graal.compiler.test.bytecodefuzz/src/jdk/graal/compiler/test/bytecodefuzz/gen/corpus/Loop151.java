package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Loop151 {
    public static int arg = 0;

    public static int test()  {
        Object o = null;
        int result = 10;
        for (int k = 0; k < arg; ++k) {
            if (o == null) {
                o = new Object();
            }
            if (k >= 5) {
                break;
            }
            result++;
        }
        return result + (o == null ? 0 : 1);
    }
}