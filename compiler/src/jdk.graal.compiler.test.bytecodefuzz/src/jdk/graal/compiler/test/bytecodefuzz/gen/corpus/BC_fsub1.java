package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_fsub1 {
    private static float a = 1.0f;
    private static float b = 1.0f;

    public static float test()  {
        return a - b;
    }
}