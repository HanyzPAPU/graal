package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class LongBits9 {
    private static long o = 0L;

    public static int test2()  {
        return Long.numberOfLeadingZeros(o);
    }
}