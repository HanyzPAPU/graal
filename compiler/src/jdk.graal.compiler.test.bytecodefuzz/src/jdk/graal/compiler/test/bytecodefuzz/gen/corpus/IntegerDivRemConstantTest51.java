package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest51 {
    public static int val = 0;

    public static int intRemZero()  {
        return val % 0;
    }
}