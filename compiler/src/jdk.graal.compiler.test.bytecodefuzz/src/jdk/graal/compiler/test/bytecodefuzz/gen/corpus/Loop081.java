package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Loop081 {
    private static int arg = 10;

    public static int test()  {
        int a = 0;
        for (int i = 0; i < arg; i++) {
            a += i;
        }
        return a;
    }
}