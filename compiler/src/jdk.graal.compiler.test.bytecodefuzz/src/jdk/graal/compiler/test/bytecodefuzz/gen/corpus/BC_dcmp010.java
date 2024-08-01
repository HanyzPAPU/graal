package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_dcmp010 {
    private static double a = 0d;
    private static double b = -0.1d;

    public static boolean test()  {
        return a < b;
    }
}