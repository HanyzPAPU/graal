package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lrem21 {
    public static long a = -9223372036854775808L;
    public static long b = 1L;

    public static long test()  {
        return a % b;
    }
}