package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_i2b9 {
    private static int a = 2;

    public static long testLong()  {
        return (byte) a;
    }
}