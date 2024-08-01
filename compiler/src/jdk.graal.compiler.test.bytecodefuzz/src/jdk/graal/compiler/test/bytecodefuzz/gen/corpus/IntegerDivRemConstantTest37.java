package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest37 {
    public static long val = 214423L;

    public static long longDivLongMinOdd()  {
        return val / (Long.MIN_VALUE + 1);
    }
}