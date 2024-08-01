package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ifeq3 {
    public static int b = 0xffff;

    public static int tests()  {
        short x = (short) b;
        int y = x & 0xffff;
        if (y == 0xffff) {
            // Just do anything else to force jump instead of conditional move
            y = (int) (System.currentTimeMillis() >> 32);
        }
        return y;
    }
}