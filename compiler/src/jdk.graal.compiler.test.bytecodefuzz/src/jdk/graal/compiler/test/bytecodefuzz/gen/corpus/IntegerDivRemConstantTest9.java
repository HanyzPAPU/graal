package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest9 {
    public static int val = Integer.MIN_VALUE;

    public static int intDivIntegerMax()  {
        return val / Integer.MAX_VALUE;
    }
}