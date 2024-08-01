package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_iadd25 {
    public static byte a = ((byte) 127);
    public static byte b = ((byte) 1);

    public static int test()  {
        return a + b;
    }
}