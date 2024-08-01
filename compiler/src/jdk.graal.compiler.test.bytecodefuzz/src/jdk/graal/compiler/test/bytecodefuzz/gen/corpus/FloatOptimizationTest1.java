package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Assert;
import org.junit.Test;
public class FloatOptimizationTest1 {
    private static float x = -0.0f;

    public static double test2Snippet()  {
        return x + 0.0f;
    }
}