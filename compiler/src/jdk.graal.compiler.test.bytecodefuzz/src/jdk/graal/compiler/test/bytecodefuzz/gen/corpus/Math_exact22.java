package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Math_exact22 {
    public static long a = Long.MAX_VALUE;

    public static long testLongNegExact()  {
        return Math.negateExact(a);
    }
}