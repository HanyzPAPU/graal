package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_iload_1_12 {
    private static int i = 2;

    public static int test()  {
        int arg = 0;
        return i + arg;
    }
}