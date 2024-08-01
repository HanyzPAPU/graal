package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_dsub0 {
    private static double a = 0.0d;
    private static double b = 0.0d;

    public static double test()  {
        return a - b;
    }
}