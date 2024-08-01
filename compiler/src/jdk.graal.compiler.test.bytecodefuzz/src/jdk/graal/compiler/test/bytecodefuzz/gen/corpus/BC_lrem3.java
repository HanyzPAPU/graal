package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lrem3 {
    private static long a = 135L;
    private static long b = 7L;

    public static long test()  {
        return a % b;
    }
}