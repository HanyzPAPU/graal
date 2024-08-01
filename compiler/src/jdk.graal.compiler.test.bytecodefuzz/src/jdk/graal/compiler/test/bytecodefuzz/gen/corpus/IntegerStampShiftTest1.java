package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerStampShiftTest1 {
    public static boolean f = false;

    public static int unsignedShiftNegativeInt()  {
        int h = f ? 0xFFFFFFF0 : 0xFFFFFF00;
        return h >>> 8;
    }
}