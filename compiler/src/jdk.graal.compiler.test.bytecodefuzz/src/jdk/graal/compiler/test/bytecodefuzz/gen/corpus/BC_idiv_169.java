package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_idiv_169 {
    private static int i = 1;
    private static int arg = 17;

    public static int test()  {
        if (i == 0) {
            final int constant = 16;
            return arg / constant;
        }
        return arg / 16;
    }
}