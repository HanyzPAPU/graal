package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_iload_02 {
    private static int arg = 2;

    public static int test()  {
        return arg;
    }
}