package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Loop112 {
    public static int a = 5;

    public static int test()  {
        int arg = a;
        int v = 0;
        while (arg-- > 0) {
            v = 1;
        }
        return v;
    }
}