package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest30 {
    public static long val = -43L;

    public static long longDivNegativeConstant()  {
        return val / -413;
    }
}