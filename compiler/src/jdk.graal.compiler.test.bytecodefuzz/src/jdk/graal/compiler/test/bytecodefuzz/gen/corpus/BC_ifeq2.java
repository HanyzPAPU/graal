package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ifeq2 {
    private static int b = 0xff;

    public static int testb()  {
        byte x = (byte) b;
        int y = x & 0xff;
        if (y == 0xff) {
            // Just do anything else to force jump instead of conditional move
            y = (int) (System.currentTimeMillis() >> 32);
        }
        return y;
    }
}