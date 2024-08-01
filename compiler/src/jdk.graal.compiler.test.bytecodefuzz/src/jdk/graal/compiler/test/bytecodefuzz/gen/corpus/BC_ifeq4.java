package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ifeq4 {
    public static int b = 0xffff;

    public static int testc()  {
        char x = (char) b;
        int y = x & 0xffff;
        if (y == 0xffff) {
            // Just do anything else to force jump instead of conditional move
            y = (int) (System.currentTimeMillis() >> 32);
        }
        return y;
    }
}