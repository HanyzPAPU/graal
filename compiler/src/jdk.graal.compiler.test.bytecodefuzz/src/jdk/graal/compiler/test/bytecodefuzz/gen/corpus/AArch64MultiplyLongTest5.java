package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class AArch64MultiplyLongTest5 {
    public static int a = 0x89abcdef;
    public static int b = 0xfedcba98;

    public long signedMNegLong2()  {
        return a * (-(long) b);
    }
}