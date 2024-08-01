package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_iinc_20 {
    public static int a = 1;

    public static int test()  {
        int arg = a;
        arg += 2;
        return arg;
    }
}