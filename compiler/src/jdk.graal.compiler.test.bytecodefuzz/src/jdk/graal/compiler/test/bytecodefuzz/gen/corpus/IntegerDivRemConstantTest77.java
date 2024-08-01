package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest77 {
    public static long val = 147065L;

    public static long longRemNegativeConstant()  {
        return val % -413;
    }
}