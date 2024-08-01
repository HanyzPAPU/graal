package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_irem4 {
    public static int b = 2;

    public static int test2()  {
        return 13 % b;
    }
}