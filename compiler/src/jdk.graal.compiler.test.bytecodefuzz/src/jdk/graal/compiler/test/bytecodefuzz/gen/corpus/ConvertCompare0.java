package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class ConvertCompare0 {
    public static int a = 0;
    public static float d = 2.87f;

    public static boolean test()  {
        return a == (double) d;
    }
}