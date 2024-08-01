package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_fsub0 {
    public static float a = 0.0f;
    public static float b = 0.0f;

    public static float test()  {
        return a - b;
    }
}