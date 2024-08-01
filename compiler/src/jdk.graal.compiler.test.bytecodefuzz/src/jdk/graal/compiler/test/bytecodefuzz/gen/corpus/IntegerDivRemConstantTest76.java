package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest76 {
    private static long val = 0L;

    public static long longRemNegativeConstant()  {
        return val % -413;
    }
}