package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Test;
public class ArrayAccessInLoopToAddressTest0 {
    public static short[] array = new short[]{1, 3, 7, 9};

    public static int positiveInductionVariable()  {
        int sum = 0;
        for (int i = 0; i < array.length - 1; i++) {
            sum += array[i + 1];
        }
        return sum;
    }
}