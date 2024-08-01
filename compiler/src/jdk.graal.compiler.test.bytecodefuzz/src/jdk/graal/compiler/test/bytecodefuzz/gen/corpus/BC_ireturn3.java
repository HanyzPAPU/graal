package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ireturn3 {
    private static int a = 256;

    public static int test()  {
        return a;
    }
}