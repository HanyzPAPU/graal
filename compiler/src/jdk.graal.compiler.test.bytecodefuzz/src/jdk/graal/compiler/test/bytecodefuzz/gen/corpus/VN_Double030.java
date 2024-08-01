package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class VN_Double030 {
    private static double arg = -0.0d;

    public static double test()  {
        if (arg == -0.0d) {
            return arg;
        }
        return 0;
    }
}