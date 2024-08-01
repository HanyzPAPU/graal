package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Float_013 {
    private static float f = java.lang.Float.NaN;

    public static boolean test()  {
        return /* Float.isNaN(f); */f != f;
    }
}