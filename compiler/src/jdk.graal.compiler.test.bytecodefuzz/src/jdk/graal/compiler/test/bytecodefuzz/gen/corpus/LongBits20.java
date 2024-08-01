package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class LongBits20 {
    private static long o = 0xffffffffffffffffL;

    public static int test4()  {
        return Long.bitCount(o);
    }
}