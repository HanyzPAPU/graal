package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_iadd30 {
    private static short a = ((short) 1);
    private static short b = ((short) 2);

    public static int test()  {
        return a + b;
    }
}