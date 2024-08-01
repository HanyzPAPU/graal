package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_fcmp010 {
    public static float a = 0f;
    public static float b = -0.1f;

    public static boolean test()  {
        return a < b;
    }
}