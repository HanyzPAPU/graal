package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest1 {
    public static int val = 0;

    public static int intDivPositiveConstant()  {
        return val / 5;
    }
}