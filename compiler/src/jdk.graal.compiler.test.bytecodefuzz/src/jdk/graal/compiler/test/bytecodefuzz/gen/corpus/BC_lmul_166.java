package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lmul_166 {
    public static long arg = -1024L;

    public static long test()  {
        return arg * 16;
    }
}