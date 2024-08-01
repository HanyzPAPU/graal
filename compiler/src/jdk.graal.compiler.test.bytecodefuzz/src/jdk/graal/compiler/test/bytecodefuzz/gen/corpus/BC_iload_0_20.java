package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_iload_0_20 {
    public static int arg = 0;

    public static int test()  {
        int i = arg;
        return i;
    }
}