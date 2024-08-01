package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class AArch64MultiplyLongTest7 {
    public static int a = 0x22228888;
    public static int b = 0xaaaacccc;
    public static long c = 0x123456789abcdL;

    public long signedMAddLong2()  {
        return a * (long) b + c;
    }
}