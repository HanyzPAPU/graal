package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest6 {
    private static int val = 0;

    public static int intDivIntegerMax()  {
        return val / Integer.MAX_VALUE;
    }
}