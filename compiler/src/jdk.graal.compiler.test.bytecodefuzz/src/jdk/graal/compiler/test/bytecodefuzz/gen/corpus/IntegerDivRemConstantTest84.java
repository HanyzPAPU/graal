package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest84 {
    public static long val = Long.MIN_VALUE;

    public static long longRemZero()  {
        return val % 0;
    }
}