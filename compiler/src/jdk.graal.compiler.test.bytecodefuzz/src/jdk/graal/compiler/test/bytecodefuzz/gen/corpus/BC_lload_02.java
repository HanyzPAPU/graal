package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lload_02 {
    private static long arg = 10000L;

    public static long test()  {
        return arg;
    }
}