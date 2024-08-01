package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Long_greater025 {
    private static long i = 4L;

    public static boolean test()  {
        if (i > 5L) {
            return true;
        }
        return false;
    }
}