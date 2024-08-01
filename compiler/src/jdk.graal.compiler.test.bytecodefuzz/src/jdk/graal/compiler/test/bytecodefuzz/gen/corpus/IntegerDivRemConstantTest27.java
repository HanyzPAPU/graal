package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest27 {
    private static long val = 214423L;

    public static long longDivLongMax()  {
        return val / Long.MAX_VALUE;
    }
}