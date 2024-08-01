package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.options.OptionValues;
import org.junit.Test;
public class Math_exp3 {
    private static double arg = -1D;

    public static double test()  {
        return Math.exp(arg);
    }
}