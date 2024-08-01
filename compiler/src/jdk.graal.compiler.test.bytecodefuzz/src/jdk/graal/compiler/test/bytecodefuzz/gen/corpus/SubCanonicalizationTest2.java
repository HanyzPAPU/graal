package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Test;
public class SubCanonicalizationTest2 {
    private static int a = 0xFFFF0000;
    private static int b = 0xFFFFFFFF;

    static int snippet0()  {
        return (a | b) - (a ^ b);
    }
}