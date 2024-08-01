package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_iinc_41 {
    public static int a = 2;

    public static int test()  {
        int arg = a;
        arg += 512;
        return arg;
    }
}