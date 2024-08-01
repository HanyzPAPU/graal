package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Float_isInfinite4 {
    private static float f = Float.NEGATIVE_INFINITY;

    public static boolean snippet()  {
        return Float.isInfinite(f);
    }
}