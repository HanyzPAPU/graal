package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest38 {
    private static long val = Long.MAX_VALUE;

    public static long longDivLongMinOdd()  {
        return val / (Long.MIN_VALUE + 1);
    }
}