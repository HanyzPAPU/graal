package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class HP_allocate012 {
    private static int count = 2;

    public static int test()  {
        int sum = 0;
        for (int i = 0; i < count; i++) {
            sum += i;
        }
        return sum;
    }
}