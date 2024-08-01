package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ifnonnull_30 {
    private static Object a = (Object) null;

    public static int test()  {
        if (a != null) {
            return 1;
        }
        return 2;
    }
}