package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ifge_32 {
    private static int a = 1;
    private static int b = 1;

    public static boolean test()  {
        return a < b;
    }
}