package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_i2b2 {
    private static int a = 255;

    public static byte test()  {
        return (byte) a;
    }
}