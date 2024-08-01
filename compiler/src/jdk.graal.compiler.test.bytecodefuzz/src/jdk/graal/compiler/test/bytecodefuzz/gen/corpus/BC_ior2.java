package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ior2 {
    public static int a = 31;
    public static int b = 63;

    public static int test()  {
        return a | b;
    }
}