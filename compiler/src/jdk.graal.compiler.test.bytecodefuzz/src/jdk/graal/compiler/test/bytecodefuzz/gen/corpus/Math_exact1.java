package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Math_exact1 {
    private static int a = 1;
    private static int b = Integer.MAX_VALUE;

    public static int testIntAddExact()  {
        return Math.addExact(a, b);
    }
}