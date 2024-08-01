package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_dreturn1 {
    public static double a = 1.1d;

    public static double test()  {
        return a;
    }
}