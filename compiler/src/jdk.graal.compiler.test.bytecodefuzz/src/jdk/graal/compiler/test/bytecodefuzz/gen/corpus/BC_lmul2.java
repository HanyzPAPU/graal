package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lmul2 {
    private static long a = 33L;
    private static long b = 67L;

    public static long test()  {
        return a * b;
    }
}