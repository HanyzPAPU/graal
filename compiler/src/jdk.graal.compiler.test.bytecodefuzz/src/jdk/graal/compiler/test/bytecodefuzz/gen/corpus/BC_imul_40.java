package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_imul_40 {
    private static int arg = 0;

    public static int test()  {
        return arg * 4;
    }
}