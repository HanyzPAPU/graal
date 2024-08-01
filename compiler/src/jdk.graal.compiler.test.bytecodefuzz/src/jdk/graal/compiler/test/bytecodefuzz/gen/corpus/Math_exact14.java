package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Math_exact14 {
    public static int a = 2;
    public static int b = Integer.MIN_VALUE;

    public static int testIntMulExact()  {
        return Math.multiplyExact(a, b);
    }
}