package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Int_greater025 {
    private static int i = 4;

    public static boolean test()  {
        if (i > 5) {
            return true;
        }
        return false;
    }
}