package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class LongBits6 {
    private static long o = 0b1000L;

    public static int test3()  {
        return Long.numberOfTrailingZeros(o);
    }
}