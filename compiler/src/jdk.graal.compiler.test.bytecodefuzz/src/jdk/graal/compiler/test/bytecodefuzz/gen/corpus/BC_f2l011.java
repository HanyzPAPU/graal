package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_f2l011 {
    public static float d = 1.0f;

    public static long test()  {
        return (long) d;
    }
}