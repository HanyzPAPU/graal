package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Float_isInfinite2 {
    private static float f = 1.0f;

    public static boolean snippet()  {
        return Float.isInfinite(f);
    }
}