package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_dsub21 {
    private static double a = 17.3;

    public static double test2()  {
        return a - a;
    }
}