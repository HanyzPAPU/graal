package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import java.lang.reflect.Array;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Array_newInstance051 {
    private static int i = 2;
    private static int j = 3;

    public static boolean test()  {
        final int[] dims = {i, j};
        Class<?> javaClass;
        if (i == 2) {
            javaClass = void.class;
        } else if (i == 3) {
            javaClass = null;
        } else {
            javaClass = int.class;
        }
        return Array.newInstance(javaClass, dims) != null;
    }
}