package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Long_greaterEqual010 {
    private static long i = -9223372036854775808L;

    public static boolean test()  {
        if (i >= 0L) {
            return true;
        }
        return false;
    }
}