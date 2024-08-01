package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_i2d1 {
    private static int a = 1;

    public static double test()  {
        return a;
    }
}