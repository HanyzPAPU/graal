package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class IntegerBits22 {
    private static int value = 1;

    public static int test5()  {
        return Integer.reverseBytes((12345 | (value & 0xffff0000))) >>> 16;
    }
}