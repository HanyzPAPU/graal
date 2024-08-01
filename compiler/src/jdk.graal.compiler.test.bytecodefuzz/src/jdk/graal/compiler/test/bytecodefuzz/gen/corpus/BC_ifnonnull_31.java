package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ifnonnull_31 {
    public static Object a = "";

    public static int test()  {
        if (a != null) {
            return 1;
        }
        return 2;
    }
}