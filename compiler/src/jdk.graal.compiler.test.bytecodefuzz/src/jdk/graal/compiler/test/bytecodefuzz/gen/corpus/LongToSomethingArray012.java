package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.jtt.JTTTest;
import org.junit.Test;
public class LongToSomethingArray012 {
    private static long arg = 0x1122_3344_5566_7788L;

    public static int[] longToIntArray()  {
        long l = arg;
        int[] ret = new int[2];
        for (int i = 0; i < 2; i++) {
            ret[i] = (int) (l & 0xffff_ffff);
            l = l >> 32;
        }
        return ret;
    }
}