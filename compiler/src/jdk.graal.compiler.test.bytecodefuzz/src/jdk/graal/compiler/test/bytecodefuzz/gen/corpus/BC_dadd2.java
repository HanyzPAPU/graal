package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_dadd2 {
    private static double a = 253.11d;
    private static double b = 54.43d;

    public static double test()  {
        return a + b;
    }
}