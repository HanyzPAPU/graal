package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest63 {
    public static int val = Integer.MAX_VALUE;

    public static int intRemMin()  {
        return val % Integer.MIN_VALUE;
    }
}