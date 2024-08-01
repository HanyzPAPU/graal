package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerStampShiftTest3 {
    public static boolean f = false;

    public static long unsignedShiftNegativeLong()  {
        long h = f ? 0xFFFFFFFFFFFFFFF0L : 0xFFFFFFFFFFFFFF00L;
        return h >>> 8;
    }
}