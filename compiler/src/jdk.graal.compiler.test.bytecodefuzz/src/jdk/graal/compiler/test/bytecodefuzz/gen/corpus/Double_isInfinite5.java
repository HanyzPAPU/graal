package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Double_isInfinite5 {
    private static double d = Double.NaN;

    public static boolean snippet()  {
        return Double.isInfinite(d);
    }
}