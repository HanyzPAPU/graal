package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class AArch64MultiplyLongTest0 {
    private static int a = 0x12345678;
    private static int b = 0x87654321;

    public long signedMulLong()  {
        return a * (long) b;
    }
}