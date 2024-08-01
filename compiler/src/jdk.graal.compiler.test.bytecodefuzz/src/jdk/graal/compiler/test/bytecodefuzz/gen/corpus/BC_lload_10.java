package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lload_10 {
    private static int i = 1;
    private static long arg = 1L;

    public static long test()  {
        return arg;
    }
}