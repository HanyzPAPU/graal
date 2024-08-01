package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class HP_allocate040 {
    public static int count = 80;

    public static int test()  {
        int[] a = new int[count];

        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }

        int i = 0;
        int iwrap = count - 1;
        int sum = 0;

        while (i < count) {
            sum += (a[i] + a[iwrap]) / 2;
            iwrap = i;
            i++;
        }
        return sum;
    }
}