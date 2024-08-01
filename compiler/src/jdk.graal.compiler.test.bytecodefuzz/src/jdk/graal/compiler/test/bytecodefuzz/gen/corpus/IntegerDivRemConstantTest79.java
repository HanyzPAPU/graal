package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest79 {
    public static long val = Long.MIN_VALUE;

    public static long longRemNegativeConstant()  {
        return val % -413;
    }
}