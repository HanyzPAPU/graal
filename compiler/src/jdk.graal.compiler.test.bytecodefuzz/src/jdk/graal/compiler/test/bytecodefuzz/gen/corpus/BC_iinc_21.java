package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_iinc_21 {
    private static int a = 2;

    public static int test()  {
        int arg = a;
        arg += 2;
        return arg;
    }
}