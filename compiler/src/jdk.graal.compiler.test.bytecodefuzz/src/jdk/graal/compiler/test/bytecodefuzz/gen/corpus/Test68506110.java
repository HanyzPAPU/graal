package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Test68506110 {
    

    public static int test()  {
        // for (int j = 0; j < 5; ++j) {
        long x = 0;
        for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; ++i) {
            x += i;
        }
        if (x != -4294967295L) {
            return 97;
        }
        // }
        return 95;
    }
}