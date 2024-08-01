package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Test;
public class NumberOfTrailingZeroings0034 {
    

    public static boolean numberOfTrailingZerosMax()  {
        return Integer.numberOfTrailingZeros(Integer.MAX_VALUE) == 0;
    }
}