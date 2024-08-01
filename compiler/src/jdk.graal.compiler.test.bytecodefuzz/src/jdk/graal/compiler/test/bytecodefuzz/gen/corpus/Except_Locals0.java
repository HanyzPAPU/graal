package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Except_Locals0 {
    private static String a = null;
    private static String b = null;

    public static int test()  {
        int x = 0;
        try {
            x = 1;
            a.toString();
            x = 2;
            b.toString();
        } catch (NullPointerException e) {
            return x;
        }
        return -1;
    }
}