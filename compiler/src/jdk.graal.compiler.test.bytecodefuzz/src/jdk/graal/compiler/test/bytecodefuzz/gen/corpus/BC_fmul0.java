package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_fmul0 {
    private static float a = 311.0f;
    private static float b = 10f;

    public static float test()  {
        return a * b;
    }
}