package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_freturn2 {
    public static float a = -1.4f;

    public static float test()  {
        return a;
    }
}