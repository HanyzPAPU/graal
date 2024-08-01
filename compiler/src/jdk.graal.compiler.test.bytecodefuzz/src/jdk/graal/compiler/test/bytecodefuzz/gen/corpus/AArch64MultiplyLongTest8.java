package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class AArch64MultiplyLongTest8 {
    public static int a = 0x99995555;
    public static int b = 0xeeeebbbb;
    public static long c = 0x3456789abcdefL;

    public long signedMSubLong()  {
        return c - a * (long) b;
    }
}