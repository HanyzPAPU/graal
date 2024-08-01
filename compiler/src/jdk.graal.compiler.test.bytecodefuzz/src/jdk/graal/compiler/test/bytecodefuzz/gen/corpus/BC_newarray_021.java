package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class BC_newarray_021 {
    public static int l = 1;

    public static byte[] test()  {
        return new byte[l];
    }
}