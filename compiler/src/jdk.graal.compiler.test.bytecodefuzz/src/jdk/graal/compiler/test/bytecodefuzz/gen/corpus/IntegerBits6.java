package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class IntegerBits6 {
    public static int o = 0b1000;

    public static int test3()  {
        return Integer.numberOfTrailingZeros(o);
    }
}