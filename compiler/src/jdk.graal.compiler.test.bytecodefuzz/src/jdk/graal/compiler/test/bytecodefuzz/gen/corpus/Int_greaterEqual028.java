package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Int_greaterEqual028 {
    private static int i = 2147483647;

    public static boolean test()  {
        if (i >= 5) {
            return true;
        }
        return false;
    }
}