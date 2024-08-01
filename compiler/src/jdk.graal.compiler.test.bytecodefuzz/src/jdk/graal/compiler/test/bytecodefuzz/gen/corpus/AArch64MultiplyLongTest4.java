package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import org.junit.Test;
public class AArch64MultiplyLongTest4 {
    public static int a = 0x89abcdef;
    public static int b = 0xfedcba98;

    public long signedMNegLong1()  {
        return -(a * (long) b);
    }
}