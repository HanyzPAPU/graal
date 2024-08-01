package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_dmul1 {
    private static double a = 11.2D;
    private static double b = 2.0D;

    public static double test()  {
        return a * b;
    }
}