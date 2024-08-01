package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class LongToSomethingArray010 {
    private static long arg = 0x1122_3344_5566_7788L;

    public static byte[] longToByteArray()  {
        long l = arg;
        byte[] ret = new byte[8];
        for (int i = 0; i < 8; i++) {
            ret[i] = (byte) (l & 0xff);
            l = l >> 8;
        }
        return ret;
    }
}