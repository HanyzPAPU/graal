package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_dcmp060 {
    public static double a = -1.0d;

    public static boolean test()  {
        return 0.0 < (a / a);
    }
}