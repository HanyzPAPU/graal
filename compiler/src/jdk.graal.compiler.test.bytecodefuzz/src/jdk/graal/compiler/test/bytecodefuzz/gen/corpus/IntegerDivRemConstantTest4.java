package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest4 {
    private static int val = Integer.MIN_VALUE;

    public static int intDivPositiveConstant()  {
        return val / 5;
    }
}