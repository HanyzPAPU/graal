package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lshr3 {
    private static long a = 6L;
    private static int b = 4;

    public static long test()  {
        return a >> b;
    }
}