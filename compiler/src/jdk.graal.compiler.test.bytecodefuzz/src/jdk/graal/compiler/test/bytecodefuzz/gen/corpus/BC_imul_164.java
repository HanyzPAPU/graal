package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_imul_164 {
    private static int i = 0;
    private static int arg = -16;

    public static int test()  {
        if (i == 0) {
            final int mult = 16;
            return arg * mult;
        }
        return arg * 16;
    }
}