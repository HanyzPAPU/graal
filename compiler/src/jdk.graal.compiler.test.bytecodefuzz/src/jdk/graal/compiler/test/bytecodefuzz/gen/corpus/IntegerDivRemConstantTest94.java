package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest94 {
    public static long val = Long.MIN_VALUE;

    public static long longRemMin()  {
        return val % Long.MIN_VALUE;
    }
}