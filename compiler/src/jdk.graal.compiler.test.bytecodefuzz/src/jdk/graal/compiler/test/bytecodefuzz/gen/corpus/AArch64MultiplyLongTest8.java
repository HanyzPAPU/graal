package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class AArch64MultiplyLongTest8 {
    private static int a = 0x99995555;
    private static int b = 0xeeeebbbb;
    private static long c = 0x3456789abcdefL;

    public long signedMSubLong()  {
        return c - a * (long) b;
    }
}