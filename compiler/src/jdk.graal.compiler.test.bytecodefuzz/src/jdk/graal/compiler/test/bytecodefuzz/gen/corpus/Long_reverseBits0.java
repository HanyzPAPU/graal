package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Long_reverseBits0 {
    private static long val = 0x123456789abcde0fL;

    public static long test()  {
        return Long.reverse(val);
    }
}