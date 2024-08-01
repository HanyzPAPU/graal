package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Test;
public class SubCanonicalizationTest5 {
    private static long a = 0L;
    private static long b = 0xFFFFFFFFFFFFFFFFL;

    static long snippet1()  {
        return (a | b) - (a ^ b);
    }
}