package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_dcmp011 {
    private static double a = 78.00d;
    private static double b = 78.001d;

    public static boolean test()  {
        return a < b;
    }
}