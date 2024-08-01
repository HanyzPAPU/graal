package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_lookupswitch050 {
    private static int a = 0;

    public static Object test()  {
        switch (a) {
            default:
                return new String();
        }
    }
}