package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest61 {
    private static int val = 0;

    public static int intRemMin()  {
        return val % Integer.MIN_VALUE;
    }
}