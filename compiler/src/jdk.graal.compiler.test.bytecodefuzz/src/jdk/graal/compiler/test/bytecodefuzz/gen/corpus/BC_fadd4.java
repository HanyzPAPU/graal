package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_fadd4 {
    public static float a = Float.MAX_VALUE / 2;
    public static float b = Float.MAX_VALUE / 2;

    public static float test()  {
        return a + b;
    }
}