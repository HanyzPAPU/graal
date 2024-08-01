package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Double_isInfinite4 {
    public static double d = Double.NEGATIVE_INFINITY;

    public static boolean snippet()  {
        return Double.isInfinite(d);
    }
}