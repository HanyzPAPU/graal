package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lmul_42 {
    public static long arg = 5L;

    public static long test()  {
        return arg * 4;
    }
}