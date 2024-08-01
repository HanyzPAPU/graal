package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Int_greaterEqual014 {
    public static int i = 1;

    public static boolean test()  {
        if (i >= 0) {
            return true;
        }
        return false;
    }
}