package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Catch_NPE_011 {
    private static int a = 1;

    public static int test()  {
        try {
            if (a >= 0) {
                throw new NullPointerException();
            }
        } catch (NullPointerException npe) {
            return a;
        }
        return -1;
    }
}