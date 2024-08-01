package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest25 {
    public static long val = -1234L;

    public static long longDivLongMax()  {
        return val / Long.MAX_VALUE;
    }
}