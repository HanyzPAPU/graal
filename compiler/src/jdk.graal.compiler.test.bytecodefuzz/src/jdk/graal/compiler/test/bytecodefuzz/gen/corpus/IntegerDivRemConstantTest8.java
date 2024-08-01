package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest8 {
    private static int val = Integer.MAX_VALUE;

    public static int intDivIntegerMax()  {
        return val / Integer.MAX_VALUE;
    }
}