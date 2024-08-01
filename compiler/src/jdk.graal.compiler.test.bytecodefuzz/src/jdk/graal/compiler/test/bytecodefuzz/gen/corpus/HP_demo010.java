package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class HP_demo010 {
    private static int count = 80;

    public static int test()  {
        int sum = 0;

        for (int i = 0; i < count; i++) {
            int[] ia = new int[count];
            long[] la = new long[count];
            float[] fa = new float[count];
            double[] da = new double[count];
            sum += ia[i] = (int) (la[i] = (long) (fa[i] = (float) (da[i] = i)));
        }

        return sum;
    }
}