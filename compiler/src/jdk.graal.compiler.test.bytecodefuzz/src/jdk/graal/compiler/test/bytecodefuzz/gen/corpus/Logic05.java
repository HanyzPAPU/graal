package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Logic05 {
    private static int a = 0;
    private static int b = 32;

    public static int test()  {
        if (((a != 0 ? 1 : 0) & (a != b ? 1 : 0)) != 0) {
            return 42;
        }
        return 11;
    }
}