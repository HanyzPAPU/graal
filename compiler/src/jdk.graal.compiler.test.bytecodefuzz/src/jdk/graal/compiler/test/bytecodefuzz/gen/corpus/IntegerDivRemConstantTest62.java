package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest62 {
    private static int val = 4256;

    public static int intRemMin()  {
        return val % Integer.MIN_VALUE;
    }
}