package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_aload_10 {
    public static int i = 1;
    public static Object arg = null;

    public static Object test()  {
        return arg;
    }
}