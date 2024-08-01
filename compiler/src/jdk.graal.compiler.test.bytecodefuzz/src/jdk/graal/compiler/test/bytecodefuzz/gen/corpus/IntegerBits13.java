package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class IntegerBits13 {
    private static int o = 0x80000000;

    public static int test2()  {
        return Integer.numberOfLeadingZeros(o);
    }
}