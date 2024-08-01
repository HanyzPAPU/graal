package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest57 {
    private static int val = 4256;

    public static int intRemMax()  {
        return val % Integer.MAX_VALUE;
    }
}