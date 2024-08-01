package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest48 {
    private static int val = Integer.MAX_VALUE;

    public static int intRemNegativeConstant()  {
        return val % -139968;
    }
}