package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_d2i013 {
    private static double d = -156.82743d;

    public static int test()  {
        return (int) d;
    }
}