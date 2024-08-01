package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_iadd2 {
    public static int a = 33;
    public static int b = 67;

    public static int test()  {
        return a + b;
    }
}