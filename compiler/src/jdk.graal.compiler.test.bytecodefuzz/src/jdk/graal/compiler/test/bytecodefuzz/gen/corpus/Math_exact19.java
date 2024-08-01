package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Math_exact19 {
    public static int a = Integer.MAX_VALUE;

    public static int testIntNegExact()  {
        return Math.negateExact(a);
    }
}