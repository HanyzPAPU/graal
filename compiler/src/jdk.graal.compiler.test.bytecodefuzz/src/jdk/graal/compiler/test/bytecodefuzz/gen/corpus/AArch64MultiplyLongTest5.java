package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class AArch64MultiplyLongTest5 {
    private static int a = 0x89abcdef;
    private static int b = 0xfedcba98;

    public long signedMNegLong2()  {
        return a * (-(long) b);
    }
}