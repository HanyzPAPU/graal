package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Long_greater031 {
    public static long i = -6L;

    public static boolean test()  {
        if (i > -5L) {
            return true;
        }
        return false;
    }
}