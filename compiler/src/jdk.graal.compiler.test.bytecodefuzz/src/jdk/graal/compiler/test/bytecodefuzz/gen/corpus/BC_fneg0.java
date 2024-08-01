package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_fneg0 {
    public static float a = 0.0f;

    public static float test()  {
        return -a;
    }
}