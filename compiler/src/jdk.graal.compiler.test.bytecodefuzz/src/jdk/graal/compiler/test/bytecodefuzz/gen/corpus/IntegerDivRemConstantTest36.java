package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest36 {
    private static long val = 0L;

    public static long longDivLongMinOdd()  {
        return val / (Long.MIN_VALUE + 1);
    }
}