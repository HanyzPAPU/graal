package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_dmul0 {
    public static double a = 311.0D;
    public static double b = 10D;

    public static double test()  {
        return a * b;
    }
}