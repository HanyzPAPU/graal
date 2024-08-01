package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Math_exact7 {
    private static int a = -2;
    private static int b = Integer.MAX_VALUE;

    public static int testIntSubExact()  {
        return Math.subtractExact(a, b);
    }
}