package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest15 {
    private static int val = -123;

    public static int intDivIntegerMinOdd()  {
        return val / (Integer.MIN_VALUE + 1);
    }
}