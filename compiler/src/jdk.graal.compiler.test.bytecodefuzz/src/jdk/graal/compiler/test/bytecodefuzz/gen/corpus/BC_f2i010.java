package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_f2i010 {
    public static float d = 0.0f;

    public static int test()  {
        return (int) d;
    }
}