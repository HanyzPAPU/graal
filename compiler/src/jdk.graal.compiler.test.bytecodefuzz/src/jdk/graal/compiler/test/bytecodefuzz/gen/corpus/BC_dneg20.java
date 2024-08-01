package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_dneg20 {
    public static double a = -0.0d;

    public static double test()  {
        return 1 / (-a);
    }
}