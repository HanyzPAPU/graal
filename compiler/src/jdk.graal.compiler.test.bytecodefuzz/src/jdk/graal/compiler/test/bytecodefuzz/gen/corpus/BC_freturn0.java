package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_freturn0 {
    private static float a = 0.0f;

    public static float test()  {
        return a;
    }
}