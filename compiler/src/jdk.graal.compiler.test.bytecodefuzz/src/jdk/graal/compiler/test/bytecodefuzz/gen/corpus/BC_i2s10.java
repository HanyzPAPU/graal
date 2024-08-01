package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_i2s10 {
    public static int a = 65535;

    public static long testLong()  {
        return (short) a;
    }
}