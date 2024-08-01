package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ifnonnull_20 {
    private static Object a = (Object) null;

    public static boolean test()  {
        return a != null;
    }
}