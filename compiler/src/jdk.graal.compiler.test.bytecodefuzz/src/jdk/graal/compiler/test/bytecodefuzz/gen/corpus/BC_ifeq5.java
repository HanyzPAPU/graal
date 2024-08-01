package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ifeq5 {
    public static int b = 0xff;

    public static boolean testCondb()  {
        byte x = (byte) b;
        int y = x & 0xff;
        return y == 0xff;
    }
}