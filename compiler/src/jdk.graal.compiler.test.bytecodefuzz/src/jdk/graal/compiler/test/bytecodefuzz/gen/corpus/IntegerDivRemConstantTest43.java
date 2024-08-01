package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest43 {
    private static int val = Integer.MAX_VALUE;

    public static int intRemPositiveConstant()  {
        return val % 139968;
    }
}