package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest5 {
    private static int val = -10;

    public static int intDivIntegerMax()  {
        return val / Integer.MAX_VALUE;
    }
}