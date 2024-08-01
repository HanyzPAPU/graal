package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest47 {
    public static int val = 4256;

    public static int intRemNegativeConstant()  {
        return val % -139968;
    }
}