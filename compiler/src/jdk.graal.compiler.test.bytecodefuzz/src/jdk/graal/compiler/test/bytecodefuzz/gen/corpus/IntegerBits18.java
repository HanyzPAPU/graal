package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class IntegerBits18 {
    private static int o = 0x40000000;

    public static int test3()  {
        return Integer.numberOfTrailingZeros(o);
    }
}