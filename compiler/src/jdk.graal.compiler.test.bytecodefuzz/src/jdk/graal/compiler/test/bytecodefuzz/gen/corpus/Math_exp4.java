package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.options.OptionValues;
import org.junit.Test;
public class Math_exp4 {
    private static double arg = -0.0D;

    public static double test()  {
        return Math.exp(arg);
    }
}