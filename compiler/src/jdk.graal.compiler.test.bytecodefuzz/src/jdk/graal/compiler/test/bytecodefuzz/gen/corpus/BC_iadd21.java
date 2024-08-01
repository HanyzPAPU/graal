package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_iadd21 {
    private static byte a = ((byte) 0);
    private static byte b = ((byte) -1);

    public static int test()  {
        return a + b;
    }
}