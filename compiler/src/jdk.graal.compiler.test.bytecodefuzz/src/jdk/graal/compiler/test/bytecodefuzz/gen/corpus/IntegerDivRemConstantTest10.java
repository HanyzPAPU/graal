package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest10 {
    public static int val = -123;

    public static int intDivNegativeConstant()  {
        return val / -1234;
    }
}