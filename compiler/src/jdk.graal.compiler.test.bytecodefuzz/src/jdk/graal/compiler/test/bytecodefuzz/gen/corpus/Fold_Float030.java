package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Fold_Float030 {
    private static float x = -0f;
    private static float y = -0f;

    public static boolean test()  {
        if (x == 0) {
            if (1 / x == Float.NEGATIVE_INFINITY) {
                return 1 / y == Float.NEGATIVE_INFINITY;
            } else {
                return 1 / y == Float.POSITIVE_INFINITY;
            }
        }
        return false;
    }
}