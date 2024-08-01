package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest88 {
    private static long val = Long.MAX_VALUE;

    public static long longRemMax()  {
        return val % Long.MAX_VALUE;
    }
}