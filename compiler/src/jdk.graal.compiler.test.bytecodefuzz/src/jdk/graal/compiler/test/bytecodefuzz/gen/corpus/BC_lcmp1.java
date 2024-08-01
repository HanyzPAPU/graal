package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lcmp1 {
    public static long a = 77L;
    public static long b = 78L;

    public static boolean test()  {
        return a < b;
    }
}