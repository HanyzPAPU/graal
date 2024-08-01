package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_dsub22 {
    private static double a = Double.NaN;

    public static double test2()  {
        return a - a;
    }
}