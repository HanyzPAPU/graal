package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Long_greater012 {
    private static long i = -1L;

    public static boolean test()  {
        if (i > 0L) {
            return true;
        }
        return false;
    }
}