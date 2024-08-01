package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Float_011 {
    private static float f = 2.0f;

    public static boolean test()  {
        return /* Float.isNaN(f); */f != f;
    }
}