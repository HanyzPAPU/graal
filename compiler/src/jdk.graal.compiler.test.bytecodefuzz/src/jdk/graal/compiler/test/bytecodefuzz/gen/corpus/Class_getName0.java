package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Class_getName0 {
    private static int a = 0;

    public static String test()  {
        if (a == 0) {
            return String.class.getName();
        }
        return "";
    }
}