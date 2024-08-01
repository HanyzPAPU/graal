package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_dneg2 {
    public static double a = 7263.8734d;
    public static double b = 8263.8734d;
    public static int which = 0;

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