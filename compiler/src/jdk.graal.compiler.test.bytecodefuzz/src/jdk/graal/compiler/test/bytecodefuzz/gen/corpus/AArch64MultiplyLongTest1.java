package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class AArch64MultiplyLongTest1 {
    private static short a = (short) 32767;
    private static short b = (short) -32768;

    public long signedMulLongFromShort()  {
        return (long) a * b;
    }
}