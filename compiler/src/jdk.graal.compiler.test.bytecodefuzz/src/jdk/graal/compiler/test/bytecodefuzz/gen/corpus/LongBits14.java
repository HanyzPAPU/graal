package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class LongBits14 {
    public static long o = 0L;

    public static int test4()  {
        return Long.bitCount(o);
    }
}