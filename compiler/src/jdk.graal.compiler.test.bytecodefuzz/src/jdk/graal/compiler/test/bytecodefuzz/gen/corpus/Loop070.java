package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Loop070 {
    public static int arg = 0;

    public static String test()  {
        int count = arg;
        for (int i = 0; i < arg; i++) {
            count++;
        }
        return "ok" + count;
    }
}