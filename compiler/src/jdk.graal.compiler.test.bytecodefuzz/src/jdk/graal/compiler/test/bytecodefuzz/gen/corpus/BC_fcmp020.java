package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_fcmp020 {
    private static float a = -1.0f;

    public static boolean test()  {
        return (a / a) < 0.0f;
    }
}