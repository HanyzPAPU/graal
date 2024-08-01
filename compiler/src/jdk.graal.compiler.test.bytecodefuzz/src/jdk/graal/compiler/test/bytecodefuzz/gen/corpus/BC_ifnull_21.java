package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ifnull_21 {
    public static Object a = "";

    public static boolean test()  {
        return a == null;
    }
}