package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_iload_31 {
    public static int i = 1;
    public static int j = 1;
    public static int k = 1;
    public static int arg = -1;

    public static int test()  {
        return arg;
    }
}