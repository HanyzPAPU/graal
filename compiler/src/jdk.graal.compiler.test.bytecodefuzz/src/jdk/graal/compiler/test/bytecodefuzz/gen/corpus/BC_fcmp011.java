package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_fcmp011 {
    private static float a = 78.00f;
    private static float b = 78.001f;

    public static boolean test()  {
        return a < b;
    }
}