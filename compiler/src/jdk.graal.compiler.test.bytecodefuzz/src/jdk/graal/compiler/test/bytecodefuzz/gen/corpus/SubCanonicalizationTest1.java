package jdk.graal.compiler.test.bytecodefuzz.gen.corpus;
import jdk.graal.compiler.core.test.GraalCompilerTest;
import org.junit.Test;
public class SubCanonicalizationTest1 {
    public static int a = 0;
    public static int b = 0xFFFFFFFF;

    public static int snippet0()  {
        return (a | b) - (a ^ b);
    }
}