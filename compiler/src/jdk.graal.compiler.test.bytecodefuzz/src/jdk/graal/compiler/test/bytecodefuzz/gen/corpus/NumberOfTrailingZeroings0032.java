package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Test;
public class NumberOfTrailingZeroings0032 {
    

    public static boolean numberOfTrailingZerosM1()  {
        return Integer.numberOfTrailingZeros(-1) == 0;
    }
}