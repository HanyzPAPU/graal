package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import jdk.graal.compiler.options.OptionValues;
import org.junit.Test;
import jdk.vm.ci.meta.ResolvedJavaMethod;
public class Math_pow9 {
    public static double x = Double.NaN;
    public static double y = 23d;

    public static double test()  {
        return Math.pow(x, y);
    }
}