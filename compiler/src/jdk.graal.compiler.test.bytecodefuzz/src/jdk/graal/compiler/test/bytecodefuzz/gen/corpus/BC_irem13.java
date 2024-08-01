package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_irem13 {
    private static int a = Integer.MAX_VALUE;
    private static int b = Integer.MAX_VALUE;

    public static int test5()  {
        return (a + 0xFF) % (b + 0xFF);
    }
}