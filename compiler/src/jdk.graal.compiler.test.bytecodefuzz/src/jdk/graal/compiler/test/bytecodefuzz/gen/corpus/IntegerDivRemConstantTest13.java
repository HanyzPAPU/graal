package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest13 {
    public static int val = Integer.MAX_VALUE;

    public static int intDivNegativeConstant()  {
        return val / -1234;
    }
}