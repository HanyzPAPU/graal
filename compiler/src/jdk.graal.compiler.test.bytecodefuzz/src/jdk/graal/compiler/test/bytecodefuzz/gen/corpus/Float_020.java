package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Float_020 {
    public static float f = 1.0f;

    public static boolean test()  {
        return f != 1.0f;
    }
}