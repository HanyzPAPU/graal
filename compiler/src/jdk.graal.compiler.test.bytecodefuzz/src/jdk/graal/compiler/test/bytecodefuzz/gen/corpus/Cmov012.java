package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Cmov012 {
    public static int a = 1;
    public static int b = 0;

    public static boolean test()  {
        boolean result = a < b || a == b;
        return result;
    }
}