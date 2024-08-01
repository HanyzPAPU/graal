package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lneg0 {
    private static long a = 0L;

    public static long test()  {
        return -a;
    }
}