package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_i2l2 {
    private static int a = 3;

    public static long test()  {
        return a;
    }
}