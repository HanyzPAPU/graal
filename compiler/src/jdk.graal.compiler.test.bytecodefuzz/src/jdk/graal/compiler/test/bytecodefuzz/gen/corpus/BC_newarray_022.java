package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_newarray_022 {
    private static int l = -1;

    public static byte[] test()  {
        return new byte[l];
    }
}