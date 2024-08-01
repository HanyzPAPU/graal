package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_fneg2 {
    public static float a = 7263.8734f;

    public static float test()  {
        return -a;
    }
}