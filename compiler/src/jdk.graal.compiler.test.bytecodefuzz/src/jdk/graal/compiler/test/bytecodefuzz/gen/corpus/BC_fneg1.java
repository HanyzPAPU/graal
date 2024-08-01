package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_fneg1 {
    public static float a = -1.01f;

    public static float test()  {
        return -a;
    }
}