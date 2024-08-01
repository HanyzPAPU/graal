package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Test;
public class ArrayAccessInLoopToAddressTest1 {
    public static short[] array = new short[]{1, 3, 7, 9};

    public static int negativeInductionVariable()  {
        int sum = 0;
        for (int i = -array.length; i < array.length - 4; i++) {
            sum += array[i + 4];
        }
        return sum;
    }
}