package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class AArch64MultiplyLongTest3 {
    public static byte a = (byte) 10;
    public static byte b = (byte) -256;

    public long signedMulLongFromByte()  {
        return (long) a * b;
    }
}