package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_i2s7 {
    public static int a = 32768;

    public static int testInt()  {
        return (short) a;
    }
}