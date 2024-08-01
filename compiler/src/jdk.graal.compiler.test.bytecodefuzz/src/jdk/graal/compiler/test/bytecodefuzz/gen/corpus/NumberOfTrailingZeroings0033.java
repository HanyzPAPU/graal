package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Test;
public class NumberOfTrailingZeroings0033 {
    

    public static boolean numberOfTrailingZerosMin()  {
        return Integer.numberOfTrailingZeros(Integer.MIN_VALUE) == 31;
    }
}