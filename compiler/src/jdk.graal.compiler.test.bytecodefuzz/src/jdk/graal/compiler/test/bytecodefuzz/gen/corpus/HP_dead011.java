package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class HP_dead011 {
    private static int count = 20;

    public static int test()  {
        int sum = 0;
        for (int i = 0; i <= count; i++) {
            int a = i + i;
            int b = i / 2 * i - 10;
            @SuppressWarnings("unused")
            int c = a + b;
            int d = a;
            sum += d;
        }
        return sum;
    }
}