package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lneg1 {
    public static long a = -1L;

    public static long test()  {
        return -a;
    }
}