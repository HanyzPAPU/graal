package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Class_getSimpleName023 {
    private static int i = 3;

    public static String test()  {
        if (i == 0) {
            return int.class.getSimpleName();
        }
        if (i == 1) {
            return int[].class.getSimpleName();
        }
        if (i == 2) {
            return Object[][].class.getSimpleName();
        }
        return null;
    }
}