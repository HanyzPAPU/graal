package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest28 {
    private static long val = Long.MAX_VALUE;

    public static long longDivLongMax()  {
        return val / Long.MAX_VALUE;
    }
}