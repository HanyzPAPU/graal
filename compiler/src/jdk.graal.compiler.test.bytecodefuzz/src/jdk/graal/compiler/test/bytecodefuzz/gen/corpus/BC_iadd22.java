package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_iadd22 {
    public static byte a = ((byte) 33);
    public static byte b = ((byte) 67);

    public static int test()  {
        return a + b;
    }
}