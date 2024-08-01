package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_fadd2 {
    private static float a = 253.11f;
    private static float b = 54.43f;

    public static float test()  {
        return a + b;
    }
}