package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Int_less016 {
    private static int i = 2147483647;

    public static boolean test()  {
        if (i < 0) {
            return true;
        }
        return false;
    }
}