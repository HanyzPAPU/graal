package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_isub0 {
    public static int a = 1;
    public static int b = -2;

    public static int test()  {
        return a - b;
    }
}