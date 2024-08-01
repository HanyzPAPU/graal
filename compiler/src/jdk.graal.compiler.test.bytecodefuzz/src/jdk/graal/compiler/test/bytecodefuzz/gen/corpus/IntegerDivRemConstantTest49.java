package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest49 {
    private static int val = Integer.MIN_VALUE;

    public static int intRemNegativeConstant()  {
        return val % -139968;
    }
}