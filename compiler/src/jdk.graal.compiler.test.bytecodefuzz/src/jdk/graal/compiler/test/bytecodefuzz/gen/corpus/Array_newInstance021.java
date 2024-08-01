package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import java.lang.reflect.Array;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Array_newInstance021 {
    private static int i = 2;

    public static boolean test()  {
        Class<?> javaClass;
        if (i == 2) {
            javaClass = void.class;
        } else if (i == 3) {
            javaClass = null;
        } else {
            javaClass = int.class;
        }
        return Array.newInstance(javaClass, 0) != null;
    }
}