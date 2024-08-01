package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class LongBits18 {
    private static long o = 0x3ffffffffL;

    public static int test4()  {
        return Long.bitCount(o);
    }
}