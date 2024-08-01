package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Throw_InCatch011 {
    public static int i = 1;

    public static boolean test()  throws Exception {
        if (i == 0) {
            return true;
        }
        try {
            throw new Exception();
        } catch (Exception e) {
            throw e;
        }
    }
}