package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_d2f0 {
    private static double d = 0.0d;

    public static float test()  {
        return (float) d;
    }
}