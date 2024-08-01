package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Test;
public class MathFMAConstantInputTest1 {
    

    public static float floatFMAWithPi()  {
        float[] input = {Float.MAX_VALUE, 2.0F, -Float.MAX_VALUE};
        return Math.fma(input[0], input[1], input[2]);
    }
}