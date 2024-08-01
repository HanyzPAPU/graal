package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest26 {
    private static long val = 0L;

    public static long longDivLongMax()  {
        return val / Long.MAX_VALUE;
    }
}