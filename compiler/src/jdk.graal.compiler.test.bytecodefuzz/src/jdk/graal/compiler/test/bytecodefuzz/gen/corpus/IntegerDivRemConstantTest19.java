package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest19 {
    private static int val = Integer.MIN_VALUE;

    public static int intDivIntegerMinOdd()  {
        return val / (Integer.MIN_VALUE + 1);
    }
}