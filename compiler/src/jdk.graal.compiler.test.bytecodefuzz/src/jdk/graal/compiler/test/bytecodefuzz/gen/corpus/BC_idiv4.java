package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_idiv4 {
    private static int b = 6;

    public static int testStrictlyPositive()  {
        return 64 / ((b & 7) + 1);
    }
}