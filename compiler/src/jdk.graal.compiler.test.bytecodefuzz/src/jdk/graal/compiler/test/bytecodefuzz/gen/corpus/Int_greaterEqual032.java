package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Int_greaterEqual032 {
    private static int i = -5;

    public static boolean test()  {
        if (i >= -5) {
            return true;
        }
        return false;
    }
}