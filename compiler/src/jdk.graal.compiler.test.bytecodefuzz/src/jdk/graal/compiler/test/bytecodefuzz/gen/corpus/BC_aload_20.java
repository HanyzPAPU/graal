package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_aload_20 {
    private static int i = 1;
    private static int j = 1;
    private static Object arg = null;

    public static Object test()  {
        return arg;
    }
}