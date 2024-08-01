package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_freturn4 {
    private static float a = 1000.001f;

    public static float test()  {
        return a;
    }
}