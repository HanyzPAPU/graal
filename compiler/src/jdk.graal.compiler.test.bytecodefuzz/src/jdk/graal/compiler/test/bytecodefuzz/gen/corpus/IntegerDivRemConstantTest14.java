package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest14 {
    public static int val = Integer.MIN_VALUE;

    public static int intDivNegativeConstant()  {
        return val / -1234;
    }
}