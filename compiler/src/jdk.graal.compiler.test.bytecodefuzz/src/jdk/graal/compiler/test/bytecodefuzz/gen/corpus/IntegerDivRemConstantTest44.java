package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest44 {
    public static int val = Integer.MIN_VALUE;

    public static int intRemPositiveConstant()  {
        return val % 139968;
    }
}