package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_idiv_overflow0 {
    private static int a = Integer.MIN_VALUE;
    private static int b = -1;

    public static int test()  {
        return a / (b | 1);
    }
}