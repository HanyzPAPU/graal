package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest78 {
    public static long val = Long.MAX_VALUE;

    public static long longRemNegativeConstant()  {
        return val % -413;
    }
}