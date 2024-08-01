package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class System_currentTimeMillis010 {
    

    public static int test()  {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            if (System.currentTimeMillis() - start > 0) {
                return 1;
            }
        }
        return 0;
    }
}