package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class ConvertCompare0 {
    private static int a = 0;
    private static float d = 2.87f;

    public static boolean test()  {
        return a == (double) d;
    }
}