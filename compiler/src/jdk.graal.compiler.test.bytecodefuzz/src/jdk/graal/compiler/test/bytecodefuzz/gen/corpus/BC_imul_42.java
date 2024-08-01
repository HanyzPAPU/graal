package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_imul_42 {
    public static int arg = 5;

    public static int test()  {
        return arg * 4;
    }
}