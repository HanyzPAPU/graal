package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest59 {
    private static int val = Integer.MIN_VALUE;

    public static int intRemMax()  {
        return val % Integer.MAX_VALUE;
    }
}