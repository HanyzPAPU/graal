package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class LongBits5 {
    private static long o = 0x0102030405060708L;

    public static long test()  {
        return Long.reverseBytes(o);
    }
}