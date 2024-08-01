package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest81 {
    public static long val = 0L;

    public static long longRemZero()  {
        return val % 0;
    }
}