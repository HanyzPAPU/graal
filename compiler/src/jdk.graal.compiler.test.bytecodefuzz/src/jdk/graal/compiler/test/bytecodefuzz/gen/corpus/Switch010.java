package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Switch010 {
    public static int arg = 0;

    public static int test()  {
        switch (arg) {
            default:
                return 1;
        }
    }
}