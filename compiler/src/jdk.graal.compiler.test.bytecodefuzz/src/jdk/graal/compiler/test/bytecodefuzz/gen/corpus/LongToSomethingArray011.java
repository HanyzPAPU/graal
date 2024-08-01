package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class LongToSomethingArray011 {
    public static long arg = 0x1122_3344_5566_7788L;

    public static short[] longToShortArray()  {
        long l = arg;
        short[] ret = new short[4];
        for (int i = 0; i < 4; i++) {
            ret[i] = (short) (l & 0xffff);
            l = l >> 16;
        }
        return ret;
    }
}