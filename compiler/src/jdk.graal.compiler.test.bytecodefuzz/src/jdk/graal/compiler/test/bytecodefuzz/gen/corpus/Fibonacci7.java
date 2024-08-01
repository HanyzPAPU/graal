package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Fibonacci7 {
    private static int num = 7;

    public static int test()  {
        if (num <= 0) {
            return 0;
        }
        int n1 = 0;
        int n2 = 1;
        for (int i = 1; i < num; i++) {
            final int next = n2 + n1;
            n1 = n2;
            n2 = next;
        }
        return n2;
    }
}