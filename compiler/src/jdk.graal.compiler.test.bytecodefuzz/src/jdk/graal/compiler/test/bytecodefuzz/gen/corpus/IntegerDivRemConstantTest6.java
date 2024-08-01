package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest6 {
    public static int val = 0;

    public static int intDivIntegerMax()  {
        return val / Integer.MAX_VALUE;
    }
}