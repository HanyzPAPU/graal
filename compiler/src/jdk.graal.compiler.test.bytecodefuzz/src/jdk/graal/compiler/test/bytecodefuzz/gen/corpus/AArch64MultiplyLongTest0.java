package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class AArch64MultiplyLongTest0 {
    public static int a = 0x12345678;
    public static int b = 0x87654321;

    public long signedMulLong()  {
        return a * (long) b;
    }
}