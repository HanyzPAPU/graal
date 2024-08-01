package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_newarray_023 {
    public static int l = 17 * 1024 * 1024;

    public static byte[] test()  {
        return new byte[l];
    }
}