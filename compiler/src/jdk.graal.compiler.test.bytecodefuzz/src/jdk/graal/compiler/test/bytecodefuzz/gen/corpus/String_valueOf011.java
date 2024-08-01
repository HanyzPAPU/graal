package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class String_valueOf011 {
    private static int i = 1;

    public static String test()  {
        Object result = null;
        if (i == 1) {
            result = "string";
        }
        return String.valueOf(result);
    }
}