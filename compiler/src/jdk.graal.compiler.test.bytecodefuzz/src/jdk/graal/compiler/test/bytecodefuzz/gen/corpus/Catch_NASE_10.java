package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Catch_NASE_10 {
    public static int a = -1;

    public static int test()  {
        try {
            int[] v = new int[a];
            if (v != null) {
                return v.length;
            }
            return -1;
        } catch (NegativeArraySizeException e) {
            return 100;
        }
    }
}