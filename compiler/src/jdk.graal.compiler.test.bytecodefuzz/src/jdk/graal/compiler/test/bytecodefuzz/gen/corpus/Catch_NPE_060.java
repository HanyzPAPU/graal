package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Catch_NPE_060 {
    private static String string = "";

    public static int test()  {
        try {
            return string.hashCode();
        } catch (NullPointerException npe) {
            return -1;
        }
    }
}