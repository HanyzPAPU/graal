package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_iadd33 {
    public static short a = ((short) 1);
    public static short b = ((short) -1);

    public static int test()  {
        return a + b;
    }
}