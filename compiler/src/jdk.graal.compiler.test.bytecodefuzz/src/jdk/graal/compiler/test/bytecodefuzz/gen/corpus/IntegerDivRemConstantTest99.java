package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest99 {
    public static long val = Long.MIN_VALUE;

    public static long longRemPowerOf2()  {
        return val % 4L;
    }
}