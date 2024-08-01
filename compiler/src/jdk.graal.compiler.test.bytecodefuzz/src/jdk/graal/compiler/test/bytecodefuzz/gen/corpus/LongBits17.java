package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class LongBits17 {
    private static long o = 0xffffffffL;

    public static int test4()  {
        return Long.bitCount(o);
    }
}