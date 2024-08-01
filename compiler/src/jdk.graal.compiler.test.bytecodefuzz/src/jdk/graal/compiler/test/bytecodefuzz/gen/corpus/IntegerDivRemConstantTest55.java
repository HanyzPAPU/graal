package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest55 {
    public static int val = -10;

    public static int intRemMax()  {
        return val % Integer.MAX_VALUE;
    }
}