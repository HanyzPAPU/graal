package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest18 {
    private static int val = Integer.MAX_VALUE;

    public static int intDivIntegerMinOdd()  {
        return val / (Integer.MIN_VALUE + 1);
    }
}