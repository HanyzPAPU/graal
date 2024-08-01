package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest3 {
    private static int val = Integer.MAX_VALUE;

    public static int intDivPositiveConstant()  {
        return val / 5;
    }
}