package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Loop052 {
    public static int a = 25;

    public static String test()  {
        int arg = a;
        int count = 0;
        while (--arg > 0) {
            count++;
            new Object();
        }
        return "ok" + count;
    }
}