package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ldiv2 {
    public static long a = 256L;
    public static long b = 4L;

    public static long test()  {
        return a / b;
    }
}