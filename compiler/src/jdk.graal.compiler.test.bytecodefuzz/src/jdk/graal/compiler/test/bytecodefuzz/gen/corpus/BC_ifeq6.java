package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ifeq6 {
    public static int b = 0xffff;

    public static boolean testConds()  {
        short x = (short) b;
        int y = x & 0xffff;
        return y == 0xffff;
    }
}