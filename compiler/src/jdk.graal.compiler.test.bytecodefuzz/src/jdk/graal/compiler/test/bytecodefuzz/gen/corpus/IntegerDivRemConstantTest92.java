package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest92 {
    public static long val = 147065L;

    public static long longRemMin()  {
        return val % Long.MIN_VALUE;
    }
}