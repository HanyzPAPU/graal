package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class AArch64MultiplyLongTest6 {
    private static int a = 0x22228888;
    private static int b = 0xaaaacccc;
    private static long c = 0x123456789abcdL;

    public long signedMAddLong1()  {
        return c + a * (long) b;
    }
}