package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest34 {
    public static long val = Long.MIN_VALUE;

    public static long longDivNegativeConstant()  {
        return val / -413;
    }
}