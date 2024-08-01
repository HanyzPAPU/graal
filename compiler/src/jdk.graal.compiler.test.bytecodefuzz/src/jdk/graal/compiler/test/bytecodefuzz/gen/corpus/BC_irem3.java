package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_irem3 {
    public static int a = 135;
    public static int b = 7;

    public static int test()  {
        return a % b;
    }
}