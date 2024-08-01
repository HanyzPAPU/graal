package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest33 {
    public static long val = Long.MAX_VALUE;

    public static long longDivNegativeConstant()  {
        return val / -413;
    }
}