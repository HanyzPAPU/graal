package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Test;
public class NumberOfTrailingZeroings0030 {
    

    public static boolean numberOfTrailingZeros0()  {
        return Integer.numberOfTrailingZeros(0) == 32;
    }
}