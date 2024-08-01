package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_d2f1 {
    public static double d = 1.0d;

    public static float test()  {
        return (float) d;
    }
}