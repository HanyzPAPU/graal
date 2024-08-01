package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_iload_1_13 {
    public static int i = 1000345;

    public static int test()  {
        int arg = 0;
        return i + arg;
    }
}