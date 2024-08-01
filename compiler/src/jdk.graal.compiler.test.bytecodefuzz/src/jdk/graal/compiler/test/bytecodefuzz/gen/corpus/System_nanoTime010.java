package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class System_nanoTime010 {
    

    public static int test()  {
        long start = System.nanoTime();
        for (int i = 0; i < 10000000; i++) {
            if (System.nanoTime() - start > 0) {
                return 1;
            }
        }
        return 0;
    }
}