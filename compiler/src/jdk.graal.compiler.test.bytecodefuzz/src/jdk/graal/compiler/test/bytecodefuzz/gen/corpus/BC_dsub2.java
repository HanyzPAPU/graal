package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_dsub2 {
    public static double a = 253.11d;
    public static double b = 54.43d;

    public static double test()  {
        return a - b;
    }
}