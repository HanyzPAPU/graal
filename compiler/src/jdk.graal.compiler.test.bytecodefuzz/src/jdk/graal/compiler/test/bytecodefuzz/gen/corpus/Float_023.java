package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Float_023 {
    public static float f = java.lang.Float.NaN;

    public static boolean test()  {
        return f != 1.0f;
    }
}