package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_fadd3 {
    public static float a = Float.MAX_VALUE;
    public static float b = Float.MIN_VALUE;

    public static float test()  {
        return a + b;
    }
}