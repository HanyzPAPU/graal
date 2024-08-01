package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerStampShiftTest2 {
    private static boolean f = false;

    public static long unsignedShiftPositiveLong()  {
        long h = f ? 0x7FFFFFFFFFFFFFF0L : 0x7FFFFFFFFFFFFF00L;
        return h >>> 8;
    }
}