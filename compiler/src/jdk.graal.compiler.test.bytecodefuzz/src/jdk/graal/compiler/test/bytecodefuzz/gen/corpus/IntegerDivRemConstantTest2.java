package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest2 {
    private static int val = 4256;

    public static int intDivPositiveConstant()  {
        return val / 5;
    }
}