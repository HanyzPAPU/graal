package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_fadd3 {
    private static float a = Float.MAX_VALUE;
    private static float b = Float.MIN_VALUE;

    public static float test()  {
        return a + b;
    }
}