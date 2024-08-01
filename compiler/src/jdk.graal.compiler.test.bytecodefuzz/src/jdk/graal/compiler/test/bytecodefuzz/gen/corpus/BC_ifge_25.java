package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ifge_25 {
    private static int a = -12;
    private static int b = -12;

    public static boolean test()  {
        return a >= b;
    }
}