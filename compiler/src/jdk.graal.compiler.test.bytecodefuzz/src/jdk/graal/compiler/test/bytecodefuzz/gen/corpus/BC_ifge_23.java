package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ifge_23 {
    private static int a = 0;
    private static int b = -100;

    public static boolean test()  {
        return a >= b;
    }
}