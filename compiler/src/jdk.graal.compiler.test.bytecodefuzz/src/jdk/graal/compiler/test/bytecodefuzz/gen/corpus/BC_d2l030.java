package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_d2l030 {
    private static double divider = 34.5D;

    public static long test()  {
        return (long) (((long) divider) * divider);
    }
}