package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest50 {
    public static int val = -10;

    public static int intRemZero()  {
        return val % 0;
    }
}