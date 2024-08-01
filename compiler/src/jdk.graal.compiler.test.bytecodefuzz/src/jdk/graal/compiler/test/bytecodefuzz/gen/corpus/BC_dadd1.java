package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_dadd1 {
    private static double a = 1.0d;
    private static double b = 1.0d;

    public static double test()  {
        return a + b;
    }
}