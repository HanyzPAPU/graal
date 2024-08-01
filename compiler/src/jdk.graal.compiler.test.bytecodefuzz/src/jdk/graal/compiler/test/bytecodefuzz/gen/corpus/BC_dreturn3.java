package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_dreturn3 {
    public static double a = 256.33d;

    public static double test()  {
        return a;
    }
}