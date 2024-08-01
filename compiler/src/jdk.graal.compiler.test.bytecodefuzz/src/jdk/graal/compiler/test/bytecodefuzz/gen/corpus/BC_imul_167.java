package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_imul_167 {
    public static int i = 0;
    public static int arg = -2147483648;

    public static int test()  {
        if (i == 0) {
            final int mult = 16;
            return arg * mult;
        }
        return arg * 16;
    }
}