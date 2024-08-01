package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_fcmp082 {
    private static float a = 0.0f;

    public static boolean test()  {
        return 0.0f <= (a / a);
    }
}