package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_irem43 {
    public static int a = 16;

    public static int test()  {
        return a % 8;
    }
}