package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_aload_00 {
    private static Object arg = (Object) null;

    public static Object test()  {
        return arg;
    }
}