package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Long_reverseBytes010 {
    private static long val = 0x1122334455667708L;

    public static long test()  {
        return Long.reverseBytes(val);
    }
}