package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class IntegerBits16 {
    private static int o = 0xFFFFFFFF;

    public static int test3()  {
        return Integer.numberOfTrailingZeros(o);
    }
}