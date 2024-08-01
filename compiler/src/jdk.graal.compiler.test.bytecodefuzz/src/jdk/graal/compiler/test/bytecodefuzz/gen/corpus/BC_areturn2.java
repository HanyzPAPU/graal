package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_areturn2 {
    private static Object a = "this";

    public static Object test()  {
        return a;
    }
}