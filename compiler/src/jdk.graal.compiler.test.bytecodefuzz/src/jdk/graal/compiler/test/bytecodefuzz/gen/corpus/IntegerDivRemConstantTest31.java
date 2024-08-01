package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest31 {
    private static long val = 0L;

    public static long longDivNegativeConstant()  {
        return val / -413;
    }
}