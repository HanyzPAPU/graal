package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_iushr3 {
    private static int a = 6;
    private static int b = 4;

    public static int test()  {
        return a >>> b;
    }
}