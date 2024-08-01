package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.options.OptionValues;
import org.junit.Test;
public class Math_exp6 {
    private static double arg = 1.0D;

    public static double test()  {
        return Math.exp(arg);
    }
}