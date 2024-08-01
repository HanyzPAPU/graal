package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class LongBits19 {
    public static long o = 0xffffffff3L;

    public static int test4()  {
        return Long.bitCount(o);
    }
}