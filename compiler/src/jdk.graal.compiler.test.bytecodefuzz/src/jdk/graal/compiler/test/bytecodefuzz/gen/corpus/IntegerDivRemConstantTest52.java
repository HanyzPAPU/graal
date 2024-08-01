package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest52 {
    private static int val = 4256;

    public static int intRemZero()  {
        return val % 0;
    }
}