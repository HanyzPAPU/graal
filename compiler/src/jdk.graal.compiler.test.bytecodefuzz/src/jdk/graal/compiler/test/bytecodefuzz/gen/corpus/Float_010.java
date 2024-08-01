package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Float_010 {
    private static float f = 1.0f;

    public static boolean test()  {
        return /* Float.isNaN(f); */f != f;
    }
}