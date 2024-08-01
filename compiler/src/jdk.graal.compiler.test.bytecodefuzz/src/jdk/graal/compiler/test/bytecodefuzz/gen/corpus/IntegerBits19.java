package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class IntegerBits19 {
    private static int o = 0x80000000;

    public static int test4()  {
        return Integer.bitCount(o);
    }
}