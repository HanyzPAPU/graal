package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest35 {
    private static long val = -1234L;

    public static long longDivLongMinOdd()  {
        return val / (Long.MIN_VALUE + 1);
    }
}