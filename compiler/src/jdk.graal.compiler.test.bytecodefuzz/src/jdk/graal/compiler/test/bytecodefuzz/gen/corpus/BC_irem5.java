package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_irem5 {
    private static int b = 20000000;

    public static int test2()  {
        return 13 % b;
    }
}