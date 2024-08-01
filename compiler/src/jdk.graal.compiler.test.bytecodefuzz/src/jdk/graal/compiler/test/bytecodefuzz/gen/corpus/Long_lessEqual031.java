package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Long_lessEqual031 {
    private static long i = -6L;

    public static boolean test()  {
        if (i <= -5L) {
            return true;
        }
        return false;
    }
}