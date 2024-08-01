package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Long_greaterEqual028 {
    private static long i = 9223372036854775807L;

    public static boolean test()  {
        if (i >= 5L) {
            return true;
        }
        return false;
    }
}