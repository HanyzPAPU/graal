package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_freturn1 {
    private static float a = 1.1f;

    public static float test()  {
        return a;
    }
}