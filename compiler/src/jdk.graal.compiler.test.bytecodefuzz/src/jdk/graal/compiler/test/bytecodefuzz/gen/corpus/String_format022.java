package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class String_format022 {
    private static int val = -2147483648;

    public static String test()  {
        return String.format("Hello %d", val);
    }
}