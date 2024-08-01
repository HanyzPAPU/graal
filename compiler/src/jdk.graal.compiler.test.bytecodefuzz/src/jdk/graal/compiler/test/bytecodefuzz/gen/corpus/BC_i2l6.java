package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_i2l6 {
    private static int a = 2147483647;

    public static long test()  {
        return a;
    }
}