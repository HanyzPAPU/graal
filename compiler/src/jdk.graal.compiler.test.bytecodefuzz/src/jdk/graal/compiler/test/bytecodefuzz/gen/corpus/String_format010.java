package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class String_format010 {
    public static String s = "World";

    public static String test()  {
        return String.format("Hello %s", s);
    }
}