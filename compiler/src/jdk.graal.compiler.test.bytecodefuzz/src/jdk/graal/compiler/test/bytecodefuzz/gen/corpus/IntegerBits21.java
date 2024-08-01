package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class IntegerBits21 {
    public static int o = 0x00000001;

    public static int test4()  {
        return Integer.bitCount(o);
    }
}