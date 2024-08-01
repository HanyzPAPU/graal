package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_d2l010 {
    public static double d = 0.0d;

    public static long test()  {
        return (long) d;
    }
}