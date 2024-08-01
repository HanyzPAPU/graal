package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Assert;
import org.junit.Test;
public class FloatOptimizationTest2 {
    public static double x = -0.0;

    public static double test3Snippet()  {
        return x - -0.0;
    }
}