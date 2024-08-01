package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Float_isInfinite5 {
    public static float f = Float.NaN;

    public static boolean snippet()  {
        return Float.isInfinite(f);
    }
}