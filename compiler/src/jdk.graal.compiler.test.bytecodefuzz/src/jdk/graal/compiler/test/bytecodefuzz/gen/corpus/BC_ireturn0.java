package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ireturn0 {
    private static int a = 0;

    public static int test()  {
        return a;
    }
}