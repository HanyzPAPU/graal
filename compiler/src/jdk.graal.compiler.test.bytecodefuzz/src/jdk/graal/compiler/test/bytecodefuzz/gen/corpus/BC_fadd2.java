package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_fadd2 {
    public static float a = 253.11f;
    public static float b = 54.43f;

    public static float test()  {
        return a + b;
    }
}