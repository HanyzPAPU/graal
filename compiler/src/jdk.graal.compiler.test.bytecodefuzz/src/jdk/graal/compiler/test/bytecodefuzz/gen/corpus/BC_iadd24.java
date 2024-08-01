package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_iadd24 {
    private static byte a = ((byte) -128);
    private static byte b = ((byte) 1);

    public static int test()  {
        return a + b;
    }
}