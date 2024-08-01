package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_fcmp100 {
    private static int x = 0;

    public static boolean test()  {
        float a = 0;
        float b = 0;
        switch (x) {
            case 0:
                a = Float.POSITIVE_INFINITY;
                b = 1;
                break;
            case 1:
                a = 1;
                b = Float.POSITIVE_INFINITY;
                break;
            case 2:
                a = Float.NEGATIVE_INFINITY;
                b = 1;
                break;
            case 3:
                a = 1;
                b = Float.NEGATIVE_INFINITY;
                break;
            case 4:
                a = Float.NEGATIVE_INFINITY;
                b = Float.NEGATIVE_INFINITY;
                break;
            case 5:
                a = Float.NEGATIVE_INFINITY;
                b = Float.POSITIVE_INFINITY;
                break;
            case 6:
                a = Float.NaN;
                b = Float.POSITIVE_INFINITY;
                break;
            case 7:
                a = 1;
                b = Float.NaN;
                break;
            case 8:
                a = 1;
                b = -0.0f / 0.0f;
                break;
        }
        return a <= b;
    }
}