package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_iushr4 {
    public static int a = -2147483648;
    public static int b = 16;

    public static int test()  {
        return a >>> b;
    }
}