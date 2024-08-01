package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lreturn3 {
    public static long a = 256L;

    public static long test()  {
        return a;
    }
}