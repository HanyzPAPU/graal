package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest11 {
    private static int val = 0;

    public static int intDivNegativeConstant()  {
        return val / -1234;
    }
}