package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_i2f2 {
    public static int a = -34;

    public static float test()  {
        return a;
    }
}