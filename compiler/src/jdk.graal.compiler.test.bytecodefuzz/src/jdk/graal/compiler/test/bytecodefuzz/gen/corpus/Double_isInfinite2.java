package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Double_isInfinite2 {
    public static double d = 1.0d;

    public static boolean snippet()  {
        return Double.isInfinite(d);
    }
}