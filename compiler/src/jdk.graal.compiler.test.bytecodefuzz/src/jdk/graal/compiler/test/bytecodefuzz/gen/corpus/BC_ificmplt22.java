package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ificmplt22 {
    private static int a = 2;

    public static int test()  {
        return a > 1 ? 13 : 12;
    }
}