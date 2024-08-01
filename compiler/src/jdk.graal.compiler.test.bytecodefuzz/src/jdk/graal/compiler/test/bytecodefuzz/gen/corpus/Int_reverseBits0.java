package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class Int_reverseBits0 {
    private static int val = 0x12345678;

    public static int test()  {
        return Integer.reverse(val);
    }
}