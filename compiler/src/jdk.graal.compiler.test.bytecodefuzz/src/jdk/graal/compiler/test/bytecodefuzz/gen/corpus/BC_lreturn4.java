package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lreturn4 {
    private static long a = 1000000000000L;

    public static long test()  {
        return a;
    }
}