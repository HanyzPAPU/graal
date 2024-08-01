package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Catch_NPE_032 {
    public static int a = -2;

    public static int test()  {
        try {
            if (a >= 0) {
                final Object o = null;
                return o.hashCode();
            }
        } catch (NullPointerException npe) {
            return a;
        }
        return -1;
    }
}