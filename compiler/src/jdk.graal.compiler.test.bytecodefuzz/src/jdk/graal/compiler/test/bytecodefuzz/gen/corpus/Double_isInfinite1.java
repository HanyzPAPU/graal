package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Double_isInfinite1 {
    private static double d = -0.0d;

    public static boolean snippet()  {
        return Double.isInfinite(d);
    }
}