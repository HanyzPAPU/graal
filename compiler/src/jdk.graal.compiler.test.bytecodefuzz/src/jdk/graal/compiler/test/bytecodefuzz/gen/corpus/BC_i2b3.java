package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_i2b3 {
    public static int a = 128;

    public static byte test()  {
        return (byte) a;
    }
}