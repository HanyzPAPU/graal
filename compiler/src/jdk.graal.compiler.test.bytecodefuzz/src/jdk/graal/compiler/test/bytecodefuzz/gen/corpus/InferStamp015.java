package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class InferStamp015 {
    private static int arg = 0xf788_99aa;

    public static int testi2()  {
        int a = arg;
        for (int i = 0; i < 2; i++) {
            a = a << 16;
        }
        return a;
    }
}