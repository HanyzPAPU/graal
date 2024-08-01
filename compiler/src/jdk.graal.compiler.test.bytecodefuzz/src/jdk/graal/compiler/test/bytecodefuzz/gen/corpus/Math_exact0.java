package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Math_exact0 {
    public static int a = 1;
    public static int b = 2;

    public static int testIntAddExact()  {
        return Math.addExact(a, b);
    }
}