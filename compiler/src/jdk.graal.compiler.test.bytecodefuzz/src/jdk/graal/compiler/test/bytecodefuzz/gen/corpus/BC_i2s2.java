package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_i2s2 {
    private static int a = 65535;

    public static short test()  {
        return (short) a;
    }
}