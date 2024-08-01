package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest60 {
    private static int val = -10;

    public static int intRemMin()  {
        return val % Integer.MIN_VALUE;
    }
}