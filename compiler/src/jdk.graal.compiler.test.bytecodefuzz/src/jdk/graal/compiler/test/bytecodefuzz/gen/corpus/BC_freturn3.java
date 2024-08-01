package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_freturn3 {
    public static float a = 256.33f;

    public static float test()  {
        return a;
    }
}