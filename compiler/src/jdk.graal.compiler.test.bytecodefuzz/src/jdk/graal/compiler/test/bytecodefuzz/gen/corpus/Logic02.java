package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Logic02 {
    public static int a = 33;
    public static int b = 66;

    public static int test()  {
        if (((a != 0 ? 1 : 0) & (a != b ? 1 : 0)) != 0) {
            return 42;
        }
        return 11;
    }
}