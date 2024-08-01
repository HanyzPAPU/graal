package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class HP_allocate013 {
    private static int count = 3;

    public static int test()  {
        int sum = 0;
        for (int i = 0; i < count; i++) {
            sum += i;
        }
        return sum;
    }
}