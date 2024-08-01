package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_iadd20 {
    private static byte a = ((byte) 1);
    private static byte b = ((byte) 2);

    public static int test()  {
        return a + b;
    }
}