package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_i2b10 {
    public static int a = 255;

    public static long testLong()  {
        return (byte) a;
    }
}