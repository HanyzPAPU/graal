package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_fmul1 {
    private static float a = 11.2f;
    private static float b = 2.0f;

    public static float test()  {
        return a * b;
    }
}