package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Cmov022 {
    private static double a = 1.0;
    private static double b = java.lang.Double.NaN;
    private static int v1 = 1;
    private static int v2 = 2;

    public static int test()  {
        return a < b ? v1 : v2;
    }
}