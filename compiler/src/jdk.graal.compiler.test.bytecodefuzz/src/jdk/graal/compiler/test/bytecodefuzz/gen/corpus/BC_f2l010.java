package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_f2l010 {
    private static float d = 0.0f;

    public static long test()  {
        return (long) d;
    }
}