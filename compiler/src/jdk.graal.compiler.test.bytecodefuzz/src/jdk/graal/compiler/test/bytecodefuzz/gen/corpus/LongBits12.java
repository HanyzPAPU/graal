package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class LongBits12 {
    public static long o = 0x0100000000L;

    public static int test2()  {
        return Long.numberOfLeadingZeros(o);
    }
}