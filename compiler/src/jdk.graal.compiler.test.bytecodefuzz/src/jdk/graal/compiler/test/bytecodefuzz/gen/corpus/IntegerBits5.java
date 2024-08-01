package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class IntegerBits5 {
    private static int o = 0x01020304;

    public static int test()  {
        return Integer.reverseBytes(o);
    }
}