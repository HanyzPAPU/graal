package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Except_Locals2 {
    public static String a = "";
    public static String b = "";

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