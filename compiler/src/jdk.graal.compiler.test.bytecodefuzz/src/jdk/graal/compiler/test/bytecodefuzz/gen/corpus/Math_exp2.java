package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.options.OptionValues;
import org.junit.Test;
public class Math_exp2 {
    public static double arg = java.lang.Double.POSITIVE_INFINITY;

    public static double test()  {
        return Math.exp(arg);
    }
}