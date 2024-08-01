package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class String_format023 {
    public static int val = 2147483647;

    public static String test()  {
        return String.format("Hello %d", val);
    }
}