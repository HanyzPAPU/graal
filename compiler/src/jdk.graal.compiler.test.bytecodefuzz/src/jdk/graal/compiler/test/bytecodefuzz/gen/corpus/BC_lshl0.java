package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lshl0 {
    public static long a = 1L;
    public static int b = 2;

    public static long test()  {
        return a << b;
    }
}