package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lushr2 {
    private static long a = 31L;
    private static int b = 1;

    public static long test()  {
        return a >>> b;
    }
}