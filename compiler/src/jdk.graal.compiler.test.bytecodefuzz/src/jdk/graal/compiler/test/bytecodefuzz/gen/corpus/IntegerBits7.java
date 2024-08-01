package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class IntegerBits7 {
    private static int o = 0b1000;

    public static int test2()  {
        return Integer.numberOfLeadingZeros(o);
    }
}