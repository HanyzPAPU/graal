package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_i2s11 {
    private static int a = 32768;

    public static long testLong()  {
        return (short) a;
    }
}