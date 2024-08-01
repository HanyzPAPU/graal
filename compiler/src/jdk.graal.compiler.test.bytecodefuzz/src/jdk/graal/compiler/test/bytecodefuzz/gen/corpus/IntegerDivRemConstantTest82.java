package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest82 {
    private static long val = 147065L;

    public static long longRemZero()  {
        return val % 0;
    }
}