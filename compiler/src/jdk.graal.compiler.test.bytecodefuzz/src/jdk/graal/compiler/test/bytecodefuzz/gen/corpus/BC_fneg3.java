package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_fneg3 {
    public static float a = -1.0f;
    public static float b = -1.0f;

    public static float test2()  {
        return -(a - b);
    }
}