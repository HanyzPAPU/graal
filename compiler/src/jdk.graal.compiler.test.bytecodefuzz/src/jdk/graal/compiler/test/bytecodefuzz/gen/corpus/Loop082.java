package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Loop082 {
    private static int arg = 25;

    public static int test()  {
        int a = 0;
        for (int i = 0; i < arg; i++) {
            a += i;
        }
        return a;
    }
}