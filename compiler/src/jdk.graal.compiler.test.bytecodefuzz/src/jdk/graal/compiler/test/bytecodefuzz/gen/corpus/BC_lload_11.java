package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lload_11 {
    private static int i = 1;
    private static long arg = -3L;

    public static long test()  {
        return arg;
    }
}