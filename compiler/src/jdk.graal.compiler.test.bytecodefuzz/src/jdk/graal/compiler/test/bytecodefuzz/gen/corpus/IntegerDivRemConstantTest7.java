package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest7 {
    public static int val = 4256;

    public static int intDivIntegerMax()  {
        return val / Integer.MAX_VALUE;
    }
}