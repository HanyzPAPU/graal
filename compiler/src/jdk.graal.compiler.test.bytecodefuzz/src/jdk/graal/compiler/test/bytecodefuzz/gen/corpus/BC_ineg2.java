package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ineg2 {
    public static int a = 7263;

    public static int test()  {
        return -a;
    }
}