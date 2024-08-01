package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Test67536390 {
    

    public static int test()  {
        int end = Integer.MAX_VALUE;
        int count = 0;
        for (int i = Integer.MAX_VALUE - 5; i <= end; i++) {
            count++;
            if (count > 100000) {
                return 95;
            }
        }
        return 97;
    }
}