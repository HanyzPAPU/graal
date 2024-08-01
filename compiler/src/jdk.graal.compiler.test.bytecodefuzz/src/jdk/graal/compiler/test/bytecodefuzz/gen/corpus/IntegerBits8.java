package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class IntegerBits8 {
    private static int o = 0;

    public static int test3()  {
        return Integer.numberOfTrailingZeros(o);
    }
}