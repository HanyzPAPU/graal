package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_areturn0 {
    public static Object a = (Object) null;

    public static Object test()  {
        return a;
    }
}