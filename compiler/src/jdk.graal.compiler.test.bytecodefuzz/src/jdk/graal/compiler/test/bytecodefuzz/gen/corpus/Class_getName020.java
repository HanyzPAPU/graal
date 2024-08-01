package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Class_getName020 {
    public static int i = 0;

    public static String test()  {
        if (i == 0) {
            return int.class.getName();
        }
        if (i == 1) {
            return int[].class.getName();
        }
        if (i == 2) {
            return Object[][].class.getName();
        }
        return null;
    }
}