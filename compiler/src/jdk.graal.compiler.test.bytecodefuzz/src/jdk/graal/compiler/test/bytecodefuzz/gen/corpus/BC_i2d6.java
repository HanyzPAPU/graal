package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_i2d6 {
    private static int a = Integer.valueOf(Short.MAX_VALUE);

    public static double test()  {
        return a;
    }
}