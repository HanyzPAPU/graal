package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Float_021 {
    public static float f = 2.0f;

    public static boolean test()  {
        return f != 1.0f;
    }
}