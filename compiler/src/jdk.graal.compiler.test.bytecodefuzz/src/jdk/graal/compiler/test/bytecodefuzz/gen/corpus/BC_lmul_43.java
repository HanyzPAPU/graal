package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lmul_43 {
    private static long arg = -1L;

    public static long test()  {
        return arg * 4;
    }
}