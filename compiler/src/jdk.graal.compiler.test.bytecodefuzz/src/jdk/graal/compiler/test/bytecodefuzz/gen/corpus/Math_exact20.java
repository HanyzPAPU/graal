package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Math_exact20 {
    public static int a = Integer.MIN_VALUE;

    public static int testIntNegExact()  {
        return Math.negateExact(a);
    }
}