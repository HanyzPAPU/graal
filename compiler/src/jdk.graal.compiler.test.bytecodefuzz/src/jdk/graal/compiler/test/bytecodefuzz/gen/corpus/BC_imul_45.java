package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_imul_45 {
    private static int arg = -5;

    public static int test()  {
        return arg * 4;
    }
}