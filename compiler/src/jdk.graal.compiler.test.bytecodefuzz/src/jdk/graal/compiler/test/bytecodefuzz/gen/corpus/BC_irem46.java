package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_irem46 {
    private static int a = 23;

    public static int test()  {
        return a % 8;
    }
}