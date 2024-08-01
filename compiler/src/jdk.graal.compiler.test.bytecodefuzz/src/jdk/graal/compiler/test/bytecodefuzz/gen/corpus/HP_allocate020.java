package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class HP_allocate020 {
    public static int count = 100;

    public static int test()  {
        int sum = 0;
        for (int i = 0; i < count; i++) {
            final Integer j = new Integer(i);
            sum += j;
        }
        return sum;
    }
}