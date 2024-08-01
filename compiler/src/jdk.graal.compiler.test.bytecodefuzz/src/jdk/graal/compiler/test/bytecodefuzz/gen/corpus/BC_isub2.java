package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_isub2 {
    private static int a = 33;
    private static int b = -67;

    public static int test()  {
        return a - b;
    }
}