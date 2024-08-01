package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_i2d4 {
    private static int a = Integer.MAX_VALUE;

    public static double test()  {
        return a;
    }
}