package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest85 {
    private static long val = -43L;

    public static long longRemMax()  {
        return val % Long.MAX_VALUE;
    }
}