package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_land2 {
    private static long a = 31L;
    private static long b = 63L;

    public static long test()  {
        return a & b;
    }
}