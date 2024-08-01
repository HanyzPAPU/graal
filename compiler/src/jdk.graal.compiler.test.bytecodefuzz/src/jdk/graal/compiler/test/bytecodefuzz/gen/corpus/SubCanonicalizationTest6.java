package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Test;
public class SubCanonicalizationTest6 {
    public static long a = 0xFFFFFFFF00000000L;
    public static long b = 0xFFFFFFFFFFFFFFFFL;

    static long snippet1()  {
        return (a | b) - (a ^ b);
    }
}