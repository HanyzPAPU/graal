package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_fcmp070 {
    public static float a = -1.0f;

    public static boolean test()  {
        return 0.0f > (a / a);
    }
}