package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest53 {
    private static int val = Integer.MAX_VALUE;

    public static int intRemZero()  {
        return val % 0;
    }
}