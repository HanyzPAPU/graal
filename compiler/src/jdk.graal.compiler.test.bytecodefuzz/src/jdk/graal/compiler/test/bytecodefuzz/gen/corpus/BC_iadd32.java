package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_iadd32 {
    private static short a = ((short) 33);
    private static short b = ((short) 67);

    public static int test()  {
        return a + b;
    }
}