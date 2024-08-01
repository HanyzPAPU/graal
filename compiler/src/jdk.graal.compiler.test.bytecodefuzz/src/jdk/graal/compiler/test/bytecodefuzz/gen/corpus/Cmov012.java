package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Cmov012 {
    private static int a = 1;
    private static int b = 0;

    public static boolean test()  {
        boolean result = a < b || a == b;
        return result;
    }
}