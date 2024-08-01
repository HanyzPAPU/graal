package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest32 {
    private static long val = 147065L;

    public static long longDivNegativeConstant()  {
        return val / -413;
    }
}