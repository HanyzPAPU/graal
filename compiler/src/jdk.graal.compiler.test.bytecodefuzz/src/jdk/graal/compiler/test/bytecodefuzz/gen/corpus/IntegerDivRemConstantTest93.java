package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest93 {
    private static long val = Long.MAX_VALUE;

    public static long longRemMin()  {
        return val % Long.MIN_VALUE;
    }
}