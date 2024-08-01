package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ldiv1 {
    public static long a = 2L;
    public static long b = -1L;

    public static long test()  {
        return a / b;
    }
}