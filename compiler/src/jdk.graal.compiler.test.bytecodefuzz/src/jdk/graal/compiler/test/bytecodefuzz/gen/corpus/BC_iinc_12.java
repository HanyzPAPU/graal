package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_iinc_12 {
    public static int a = 4;

    public static int test()  {
        int arg = a;
        arg += 1;
        return arg;
    }
}