package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Int_greaterEqual015 {
    private static int i = 2;

    public static boolean test()  {
        if (i >= 0) {
            return true;
        }
        return false;
    }
}