package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.options.OptionValues;
import org.junit.Test;
public class Math_exp0 {
    private static double arg = java.lang.Double.NaN;

    public static double test()  {
        return Math.exp(arg);
    }
}