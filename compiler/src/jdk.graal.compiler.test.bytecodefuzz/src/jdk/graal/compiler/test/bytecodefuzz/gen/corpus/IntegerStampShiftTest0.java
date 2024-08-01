package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class IntegerStampShiftTest0 {
    public static boolean f = false;

    public static int unsignedShiftPositiveInt()  {
        int h = f ? 0x7FFFFFF0 : 0x7FFFFF00;
        return h >>> 8;
    }
}