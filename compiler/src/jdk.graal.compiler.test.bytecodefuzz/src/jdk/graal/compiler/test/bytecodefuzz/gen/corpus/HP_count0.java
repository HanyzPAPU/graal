package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class HP_count0 {
    private static int count = 40;

    public static int test()  {
        float unusedFloat = 0;
        double dub = 0;
        int sum = 0;
        double unusedDouble = 0;
        for (int i = 0; i <= count; i++) {
            if (i > 20) {
                sum += i;
            } else {
                sum += i;
            }
            dub += sum;
        }
        return sum;
    }
}