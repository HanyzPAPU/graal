package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest86 {
    public static long val = 0L;

    public static long longRemMax()  {
        return val % Long.MAX_VALUE;
    }
}