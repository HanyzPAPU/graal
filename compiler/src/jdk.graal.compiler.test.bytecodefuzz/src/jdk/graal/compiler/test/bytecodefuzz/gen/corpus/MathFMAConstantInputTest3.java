package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Test;
public class MathFMAConstantInputTest3 {
    

    public static double doubleFMAWithPi()  {
        double[] input = {Double.MAX_VALUE, 2.0D, -Double.MAX_VALUE};
        return Math.fma(input[0], input[1], input[2]);
    }
}