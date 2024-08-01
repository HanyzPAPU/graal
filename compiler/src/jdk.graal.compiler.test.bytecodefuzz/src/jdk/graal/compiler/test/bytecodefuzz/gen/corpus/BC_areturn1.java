package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_areturn1 {
    private static Object a = "";

    public static Object test()  {
        return a;
    }
}