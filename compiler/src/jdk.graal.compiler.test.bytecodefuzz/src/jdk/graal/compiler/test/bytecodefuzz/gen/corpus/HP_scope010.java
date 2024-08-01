package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Ignore;
import org.junit.Test;
public class HP_scope010 {
    public static int count = 40;

    public static int test()  {
        int sum = 0;

        for (int k = 0; k < count; k++) {
            {
                int i = 1;
                sum += i;
            }
            {
                float f = 3;
                sum += (int) f;
            }
            {
                long l = 7;
                sum += (int) l;
            }
            {
                double d = 11;
                sum += (int) d;
            }
        }

        for (int k = 0; k < count; k++) {
            if (k < 20) {
                int i = 1;
                sum += i;
            } else {
                float f = 3;
                sum += (int) f;
            }
        }

        for (int k = 0; k < count; k++) {
            int i = 3;
            for (int j = 0; j < count; j++) {
                float f = 7;
                sum += (int) (i + f);
            }
        }

        for (int k = 0; k < count; k++) {
            for (int j = 0; j < count; j++) {
                float f = 7;
                sum += (int) (j + f);
            }
            int i = 3;
            sum += i;
        }

        return sum;
    }
}