package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class LongToSomethingArray013 {
    public static long arg = 0x1122_3344_5566_7788L;

    public static long[] longToLongArray()  {
        long l = arg;
        long[] ret = new long[1];
        for (int i = 0; i < 1; i++) {
            ret[i] = l & 0xffff_ffff_ffff_ffffL;
            l = l >> 64;
        }
        return ret;
    }
}