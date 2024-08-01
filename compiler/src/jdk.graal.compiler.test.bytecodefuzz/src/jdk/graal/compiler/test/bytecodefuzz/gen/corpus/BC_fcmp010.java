package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_fcmp010 {
    private static float a = 0f;
    private static float b = -0.1f;

    public static boolean test()  {
        return a < b;
    }
}