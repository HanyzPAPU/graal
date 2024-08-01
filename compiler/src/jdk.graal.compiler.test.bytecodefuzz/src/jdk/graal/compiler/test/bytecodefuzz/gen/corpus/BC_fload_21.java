package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_fload_21 {
    private static float i = 0f;
    private static float arg = -1.01f;

    public static float test()  {
        return arg;
    }
}