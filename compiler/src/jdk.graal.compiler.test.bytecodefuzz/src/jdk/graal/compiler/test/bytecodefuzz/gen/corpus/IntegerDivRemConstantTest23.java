package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest23 {
    public static long val = Long.MAX_VALUE;

    public static long longDivPositiveConstant()  {
        return val / 35170432;
    }
}