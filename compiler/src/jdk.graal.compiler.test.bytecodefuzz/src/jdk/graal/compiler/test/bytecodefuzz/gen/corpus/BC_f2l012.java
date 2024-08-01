package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_f2l012 {
    public static float d = -1.06f;

    public static long test()  {
        return (long) d;
    }
}