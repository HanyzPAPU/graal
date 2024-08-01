package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest83 {
    public static long val = Long.MAX_VALUE;

    public static long longRemZero()  {
        return val % 0;
    }
}