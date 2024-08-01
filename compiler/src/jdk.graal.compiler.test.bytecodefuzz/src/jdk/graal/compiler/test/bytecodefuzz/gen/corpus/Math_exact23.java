package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Math_exact23 {
    public static long a = Long.MIN_VALUE;

    public static long testLongNegExact()  {
        return Math.negateExact(a);
    }
}