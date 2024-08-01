package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_idiv2 {
    private static int a = 256;
    private static int b = 4;

    public static int test()  {
        return a / b;
    }
}