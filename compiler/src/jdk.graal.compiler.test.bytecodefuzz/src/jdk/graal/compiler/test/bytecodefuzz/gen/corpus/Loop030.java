package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Loop030 {
    private static int count = 10;

    public static int test()  {
        int i1 = 1;
        int i2 = 2;
        int i4 = 4;

        for (int i = 0; i < count; i++) {
            i1 = i2;
            i2 = 7;
            i4 = i1;
        }
        return i1 + i2 * 10 + i4 * 1000;
    }
}