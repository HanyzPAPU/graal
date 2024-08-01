package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_iadd36 {
    public static short a = ((short) -32768);
    public static short b = ((short) 1);

    public static int test()  {
        return a + b;
    }
}