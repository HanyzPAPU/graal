package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lshr022 {
    public static long arg = -1L;

    public static long test0()  {
        long a = arg >> 32;
        return a >> 32;
    }
}