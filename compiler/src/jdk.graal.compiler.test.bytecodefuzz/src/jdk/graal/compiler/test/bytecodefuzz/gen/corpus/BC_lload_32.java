package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lload_32 {
    private static int i = 1;
    private static int j = 1;
    private static int k = 1;
    private static long arg = 10000L;

    public static long test()  {
        return arg;
    }
}