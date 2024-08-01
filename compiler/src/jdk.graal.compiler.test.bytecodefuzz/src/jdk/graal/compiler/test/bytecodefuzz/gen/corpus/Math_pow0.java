package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import jdk.graal.compiler.options.OptionValues;
import org.junit.Test;
import jdk.vm.ci.meta.ResolvedJavaMethod;
public class Math_pow0 {
    public static double x = 2d;
    public static double y = 0d;

    public static double test()  {
        return Math.pow(x, y);
    }
}