package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lshl2 {
    public static long a = 31L;
    public static int b = 1;

    public static long test()  {
        return a << b;
    }
}