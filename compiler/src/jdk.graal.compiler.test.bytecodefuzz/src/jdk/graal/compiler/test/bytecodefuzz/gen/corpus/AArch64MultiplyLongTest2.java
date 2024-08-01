package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class AArch64MultiplyLongTest2 {
    public static char a = (char) 59999;
    public static char b = (char) 65535;

    public long signedMulLongFromChar()  {
        return a * (long) b;
    }
}