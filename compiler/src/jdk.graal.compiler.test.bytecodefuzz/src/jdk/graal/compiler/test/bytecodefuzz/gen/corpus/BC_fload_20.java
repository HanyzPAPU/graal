package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_fload_20 {
    private static float i = 0f;
    private static float arg = -1f;

    public static float test()  {
        return arg;
    }
}