package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class LoopUnroll0 {
    public static int input = 42;

    public static int test()  {
        int ret = 2;
        int current = input;
        for (int i = 0; i < 7; i++) {
            ret *= 2 + current;
            current /= 50;
        }
        return ret;
    }
}