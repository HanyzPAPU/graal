package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest90 {
    public static long val = -43L;

    public static long longRemMin()  {
        return val % Long.MIN_VALUE;
    }
}