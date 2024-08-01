package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Loop07_20 {
    private static int arg = 0;

    public static int test()  {
        int count = arg;
        for (int i = 0; i < arg; i++) {
            count++;
        }
        return count;
    }
}