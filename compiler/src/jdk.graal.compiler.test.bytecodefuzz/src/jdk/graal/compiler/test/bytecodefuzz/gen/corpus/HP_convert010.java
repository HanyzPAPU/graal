package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class HP_convert010 {
    private static int count = 100;

    public static int test()  {
        double sum = 0;
        for (int i = 0; i < count; i++) {
            sum = (int) sum + i;
        }
        return (int) sum;
    }
}