package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class String_format021 {
    public static int val = -11;

    public static String test()  {
        return String.format("Hello %d", val);
    }
}