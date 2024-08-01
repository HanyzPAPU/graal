package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class HP_trees010 {
    private static int count = 1000;

    public static int test()  {
        int sum = 0;
        for (int i = 0; i < count; i++) {
            if (i < 100) {
                sum += 1;
            } else if (i < 200) {
                sum += 3;
            } else if (i < 300) {
                sum += 5;
            } else if (i < 400) {
                sum += 7;
            } else if (i < 500) {
                sum += 11;
            }

            if (i % 5 == 0) {
                sum += 1;
            } else if (i % 5 == 1) {
                sum += 3;
            } else if (i % 5 == 2) {
                sum += 5;
            } else if (i % 5 == 3) {
                sum += 7;
            } else if (i % 5 == 4) {
                sum += 11;
            }
        }
        return sum;
    }
}