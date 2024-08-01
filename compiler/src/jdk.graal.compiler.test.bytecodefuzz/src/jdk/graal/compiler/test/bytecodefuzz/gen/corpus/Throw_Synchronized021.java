package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
import jdk.graal.compiler.jtt.JTTTest;
public class Throw_Synchronized021 {
    public static int i = 1;

    public static synchronized boolean test()  throws Exception {
        if (i == 0) {
            return true;
        }
        throw new Exception();
    }
}