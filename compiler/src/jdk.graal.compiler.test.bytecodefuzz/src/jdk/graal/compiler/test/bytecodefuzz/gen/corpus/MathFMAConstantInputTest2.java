package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Test;
public class MathFMAConstantInputTest2 {
    

    public static double doubleFMA()  {
        return Math.fma(2.0d, 2.0d, 2.0d);
    }
}