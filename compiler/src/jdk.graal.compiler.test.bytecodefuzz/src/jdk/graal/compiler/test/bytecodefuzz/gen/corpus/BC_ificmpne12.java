package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_ificmpne12 {
    public static int a = 2;

    public static int test()  {
        return a == 1 ? 12 : 13;
    }
}