package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest87 {
    private static long val = 147065L;

    public static long longRemMax()  {
        return val % Long.MAX_VALUE;
    }
}