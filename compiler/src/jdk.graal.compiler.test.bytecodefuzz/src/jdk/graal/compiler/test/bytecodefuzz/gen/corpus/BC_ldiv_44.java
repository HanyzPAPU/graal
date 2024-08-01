package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ldiv_44 {
    private static long arg = -4L;

    public static long test()  {
        return arg / 4;
    }
}