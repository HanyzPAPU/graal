package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ifge_24 {
    public static int a = -1;
    public static int b = 0;

    public static boolean test()  {
        return a >= b;
    }
}