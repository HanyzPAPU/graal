package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ifne0 {
    public static int a = 0;

    public static int test()  {
        int n = 0;
        if (a != 0) {
            n += 1;
        } else {
            n -= 1;
        }
        if (a == 0) {
            n -= 1;
        } else {
            n += 1;
        }
        return n;
    }
}