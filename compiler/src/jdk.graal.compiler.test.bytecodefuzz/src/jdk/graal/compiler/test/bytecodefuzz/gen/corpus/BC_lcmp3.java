package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lcmp3 {
    public static long a = 293521900824L;
    public static long b = 97726785831L;

    public static boolean test()  {
        return a < b;
    }
}