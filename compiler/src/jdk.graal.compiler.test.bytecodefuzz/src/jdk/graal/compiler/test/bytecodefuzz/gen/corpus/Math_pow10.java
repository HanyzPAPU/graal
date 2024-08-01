package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import jdk.graal.compiler.options.OptionValues;
import org.junit.Test;
import jdk.vm.ci.meta.ResolvedJavaMethod;
public class Math_pow10 {
    private static double x = 0.999998;
    private static double y = 1500000.0;

    public static double test()  {
        return Math.pow(x, y);
    }
}