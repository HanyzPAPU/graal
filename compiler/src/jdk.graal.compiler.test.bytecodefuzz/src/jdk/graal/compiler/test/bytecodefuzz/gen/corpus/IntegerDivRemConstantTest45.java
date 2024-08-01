package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest45 {
    public static int val = -10;

    public static int intRemNegativeConstant()  {
        return val % -139968;
    }
}