package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Float_012 {
    private static float f = 0.5f;

    public static boolean test()  {
        return /* Float.isNaN(f); */f != f;
    }
}