package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Fold_Double040 {
    private static double x = -0d;
    private static double y = -0d;

    public static boolean test()  {
        if (x == 0) {
            if (1 / x == Double.NEGATIVE_INFINITY) {
                return 1 / y == Double.NEGATIVE_INFINITY;
            } else {
                return 1 / y == Double.POSITIVE_INFINITY;
            }
        }
        return false;
    }
}