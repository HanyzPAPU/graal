package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_i2l5 {
    public static int a = -2147483648;

    public static long test()  {
        return a;
    }
}