package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_dneg0 {
    private static double a = 0.0d;
    private static double b = 1.0d;
    private static int which = 0;

    public static double test()  {
        double result1 = -a;
        double result2 = -b;
        double result = 0.0;
        if (which == 0) {
            result = result1;
        } else {
            result = result2;
        }
        return result;
    }
}