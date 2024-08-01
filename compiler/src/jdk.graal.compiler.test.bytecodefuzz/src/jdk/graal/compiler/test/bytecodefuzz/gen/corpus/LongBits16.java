package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class LongBits16 {
    private static long o = 0xffff00ffL;

    public static int test4()  {
        return Long.bitCount(o);
    }
}