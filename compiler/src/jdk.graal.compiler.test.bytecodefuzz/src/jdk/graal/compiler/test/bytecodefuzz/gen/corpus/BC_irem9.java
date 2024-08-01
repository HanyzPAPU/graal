package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_irem9 {
    private static int a = -200000000;

    public static int test3()  {
        return a % 13;
    }
}