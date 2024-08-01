package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Long_reverseBytes020 {
    private static long val = 0x1122334455667708L;

    public static long test()  {
        return (((val >> 56) & 0xff) << 0) | (((val >> 48) & 0xff) << 8) | (((val >> 40) & 0xff) << 16) | (((val >> 32) & 0xff) << 24) | (((val >> 24) & 0xff) << 32) | (((val >> 16) & 0xff) << 40) |
                        (((val >> 8) & 0xff) << 48) | (((val >> 0) & 0xff) << 56);
    }
}