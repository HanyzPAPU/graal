package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class InferStamp017 {
    private static long arg = 0xf344_5566_7788_99aaL;

    public static long testl0()  {
        long a = arg;
        for (long i = 0; i < 2; i++) {
            a = a >> 32;
        }
        return a;
    }
}