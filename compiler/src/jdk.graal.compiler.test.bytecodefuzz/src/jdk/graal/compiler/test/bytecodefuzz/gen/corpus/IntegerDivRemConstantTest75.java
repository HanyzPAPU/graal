package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest75 {
    public static long val = -43L;

    public static long longRemNegativeConstant()  {
        return val % -413;
    }
}