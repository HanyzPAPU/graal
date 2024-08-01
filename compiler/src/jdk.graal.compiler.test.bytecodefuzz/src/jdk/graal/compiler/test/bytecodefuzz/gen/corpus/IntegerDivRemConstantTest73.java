package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest73 {
    private static long val = Long.MAX_VALUE;

    public static long longRemPositiveConstant()  {
        return val % 35170432;
    }
}