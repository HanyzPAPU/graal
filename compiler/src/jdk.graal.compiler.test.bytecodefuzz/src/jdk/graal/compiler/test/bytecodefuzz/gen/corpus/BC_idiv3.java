package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_idiv3 {
    private static int a = 135;
    private static int b = 7;

    public static int test()  {
        return a / b;
    }
}