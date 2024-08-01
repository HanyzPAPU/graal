package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Math_exact18 {
    public static int a = 0;

    public static int testIntNegExact()  {
        return Math.negateExact(a);
    }
}