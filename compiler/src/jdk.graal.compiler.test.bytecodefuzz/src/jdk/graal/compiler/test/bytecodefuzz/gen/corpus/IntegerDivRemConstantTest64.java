package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest64 {
    public static int val = Integer.MIN_VALUE;

    public static int intRemMin()  {
        return val % Integer.MIN_VALUE;
    }
}