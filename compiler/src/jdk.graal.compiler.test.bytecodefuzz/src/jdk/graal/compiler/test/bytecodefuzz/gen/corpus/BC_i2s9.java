package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_i2s9 {
    private static int a = 34;

    public static long testLong()  {
        return (short) a;
    }
}