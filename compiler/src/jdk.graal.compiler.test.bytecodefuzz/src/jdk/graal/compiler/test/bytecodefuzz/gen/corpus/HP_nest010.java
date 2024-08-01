package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class HP_nest010 {
    public static int count = 15;

    public static int test()  {
        int sum = 0;
        for (int i = 0; i < count; i++) {
            sum += i;
            for (int j = 0; j < count; j++) {
                sum += j;
            }
            for (int j = 0; j < count; j++) {
                sum += j;
            }
        }
        return sum;
    }
}