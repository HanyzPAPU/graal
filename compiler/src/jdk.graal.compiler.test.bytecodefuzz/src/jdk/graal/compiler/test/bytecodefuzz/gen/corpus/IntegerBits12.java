package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class IntegerBits12 {
    public static int o = 0x7FFFFFFF;

    public static int test2()  {
        return Integer.numberOfLeadingZeros(o);
    }
}