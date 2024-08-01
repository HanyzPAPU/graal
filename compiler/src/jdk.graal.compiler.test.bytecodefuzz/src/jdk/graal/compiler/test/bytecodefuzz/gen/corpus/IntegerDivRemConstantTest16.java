package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest16 {
    private static int val = 0;

    public static int intDivIntegerMinOdd()  {
        return val / (Integer.MIN_VALUE + 1);
    }
}