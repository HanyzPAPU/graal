package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ificmplt10 {
    public static int a = 0;

    public static int test()  {
        return a < 1 ? 12 : 13;
    }
}