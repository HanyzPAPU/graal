package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ldiv_163 {
    public static long arg = -1L;

    public static long test()  {
        return arg / 16;
    }
}