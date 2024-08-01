package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ifeq7 {
    private static int b = 0xffff;

    public static boolean testCondc()  {
        char x = (char) b;
        int y = x & 0xffff;
        return y == 0xffff;
    }
}