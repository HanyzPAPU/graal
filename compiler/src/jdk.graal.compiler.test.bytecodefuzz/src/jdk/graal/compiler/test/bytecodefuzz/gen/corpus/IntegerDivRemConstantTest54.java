package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest54 {
    public static int val = Integer.MIN_VALUE;

    public static int intRemZero()  {
        return val % 0;
    }
}