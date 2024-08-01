package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class IntegerBits10 {
    private static int o = 0xffffffff;

    public static int test4()  {
        return Integer.bitCount(o);
    }
}