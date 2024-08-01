package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Catch_NPE_002 {
    private static int a = 1;

    public static int test()  {
        int[] array = a > 0 ? new int[3] : null;
        try {
            return array.length;
        } catch (NullPointerException npe) {
            return -1;
        }
    }
}