package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest80 {
    private static long val = -43L;

    public static long longRemZero()  {
        return val % 0;
    }
}