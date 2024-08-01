package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest91 {
    public static long val = 0L;

    public static long longRemMin()  {
        return val % Long.MIN_VALUE;
    }
}