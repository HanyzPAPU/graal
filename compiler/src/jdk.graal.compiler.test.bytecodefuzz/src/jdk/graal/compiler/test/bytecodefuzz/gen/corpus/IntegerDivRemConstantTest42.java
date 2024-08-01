package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerDivRemConstantTest42 {
    public static int val = 4256;

    public static int intRemPositiveConstant()  {
        return val % 139968;
    }
}